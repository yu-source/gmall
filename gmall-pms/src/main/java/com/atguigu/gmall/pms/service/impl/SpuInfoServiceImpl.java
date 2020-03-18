package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.SkuImagesDao;
import com.atguigu.gmall.pms.dao.SkuInfoDao;
import com.atguigu.gmall.pms.dao.SkuSaleAttrValueDao;
import com.atguigu.gmall.pms.entity.SkuImagesEntity;
import com.atguigu.gmall.pms.entity.SkuSaleAttrValueEntity;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.pms.service.ProductAttrValueService;
import com.atguigu.gmall.pms.service.SpuInfoDescService;
import com.atguigu.gmall.pms.vo.SkuInfoVo;
import com.atguigu.gmall.pms.vo.SpuInfoVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.SpuInfoDao;
import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import com.atguigu.gmall.pms.service.SpuInfoService;
import org.springframework.util.CollectionUtils;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoDao skuInfoDao;
    @Autowired
    private SkuImagesDao imagesDao;
    @Autowired
    private SkuSaleAttrValueDao saleAttrValueDao;
    @Autowired
    private GmallSmsClient smsClient;
    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );
        
        return new PageVo(page);
    }

    @Override
    public PageVo querySpuByCidPage(QueryCondition condition, Long cid) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        // 分类id判断 为0则查全站
        if(cid != 0){
            wrapper.eq("catalog_id", cid);
        }
        // 关键字判断
        String key = condition.getKey();
        if(StringUtils.isNotBlank(key)){
            wrapper.and(t -> t.eq("id", key).or().like("spu_name", key));
        }

        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(condition), wrapper);

        return new PageVo(page);
    }

    @Override
    @GlobalTransactional
    public void bigSave(SpuInfoVo spuInfoVo) {
        // 1.保存spu相关的信息（spuInfo spuInfoDesc productAttrValue）
        // 1.1.保存spuInfo信息
//        spuInfoVO.setCreateTime(new Date());
//        spuInfoVO.setUodateTime(spuInfoVO.getCreateTime());
        // 我这里使用了自动填充不用手动设置日期
        this.save(spuInfoVo);
        Long spuId = spuInfoVo.getId();
        // 1.2.保存spuInfoDesc信息
        this.spuInfoDescService.saveSpuInfoDesc(spuInfoVo, spuId);
        // 1.3.保存基本属性（productAttrValue）
        productAttrValueService.saveBaseAttrValue(spuInfoVo, spuId);
        // 2.保存sku相关的信息（需要spuId）
        saveSku(spuInfoVo, spuId);

    }

    private void saveSku(SpuInfoVo spuInfoVo, Long spuId) {
        List<SkuInfoVo> skus = spuInfoVo.getSkus();
        if(CollectionUtils.isEmpty(skus)){
            return;
        }

        skus.forEach(skuInfoVo -> {
            // 2.1. 保存skuInfo
            skuInfoVo.setSpuId(spuId);
            skuInfoVo.setSkuCode(UUID.randomUUID().toString());
            skuInfoVo.setCatalogId(spuInfoVo.getCatalogId());
            skuInfoVo.setBrandId(spuInfoVo.getBrandId());
            // 设置默认图片，如果页面传了默认图片，使用页面传的默认图片，否则取第一张图片作为默认图片
            List<String> images = skuInfoVo.getImages();
            if(!CollectionUtils.isEmpty(images)){
                skuInfoVo.setSkuDefaultImg(StringUtils.isNoneBlank(skuInfoVo.getSkuDefaultImg()) ? skuInfoVo.getSkuDefaultImg() : images.get(0));
            }
            this.skuInfoDao.insert(skuInfoVo);
            Long skuId = skuInfoVo.getSkuId();
            // 2.2. 保存sku图片信息skuImages
            if(!CollectionUtils.isEmpty(images)){
                images.forEach(image -> {
                    SkuImagesEntity imagesEntity = new SkuImagesEntity();
                    imagesEntity.setImgUrl(image);
                    imagesEntity.setSkuId(skuId);
                    if(StringUtils.equals(image, skuInfoVo.getSkuDefaultImg())){
                        imagesEntity.setDefaultImg(1);
                    }else {
                        imagesEntity.setDefaultImg(0);
                    }
                    imagesDao.insert(imagesEntity);
                });
            }
            // 2.3. 保存销售属性skuSaleAttrValue
            List<SkuSaleAttrValueEntity> saleAttrs = skuInfoVo.getSaleAttrs();
            if(!CollectionUtils.isEmpty(saleAttrs)){
                saleAttrs.forEach(skuSaleAttrValueEntity -> {
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    this.saleAttrValueDao.insert(skuSaleAttrValueEntity);
                });
            }
            // 3.保存sku营销相关信息(需要skuId)
            SkuSaleVo skuSaleVo = new SkuSaleVo();
            BeanUtils.copyProperties(skuInfoVo, skuSaleVo);
            this.smsClient.saveSkuSales(skuSaleVo);
        });
    }
}