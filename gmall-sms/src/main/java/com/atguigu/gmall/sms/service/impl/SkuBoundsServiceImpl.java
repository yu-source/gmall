package com.atguigu.gmall.sms.service.impl;

import com.atguigu.gmall.sms.dao.SkuFullReductionDao;
import com.atguigu.gmall.sms.dao.SkuLadderDao;
import com.atguigu.gmall.sms.entity.SkuFullReductionEntity;
import com.atguigu.gmall.sms.entity.SkuLadderEntity;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.sms.dao.SkuBoundsDao;
import com.atguigu.gmall.sms.entity.SkuBoundsEntity;
import com.atguigu.gmall.sms.service.SkuBoundsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("skuBoundsService")
public class SkuBoundsServiceImpl extends ServiceImpl<SkuBoundsDao, SkuBoundsEntity> implements SkuBoundsService {

    @Autowired
    private SkuFullReductionDao reductionDao;
    @Autowired
    private SkuLadderDao ladderDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SkuBoundsEntity> page = this.page(
                new Query<SkuBoundsEntity>().getPage(params),
                new QueryWrapper<SkuBoundsEntity>()
        );

        return new PageVo(page);
    }

    @Override
    @Transactional
    public void saveSkuSales(SkuSaleVo skuSaleVo) {
        Long skuId = skuSaleVo.getSkuId();
        // 3.1. 保存积分信息skuBounds
        SkuBoundsEntity skuBoundsEntity = new SkuBoundsEntity();
        skuBoundsEntity.setSkuId(skuId);
        skuBoundsEntity.setBuyBounds(skuSaleVo.getBuyBounds());
        skuBoundsEntity.setGrowBounds(skuSaleVo.getGrowBounds());
        List<Integer> works = skuSaleVo.getWork();
        // 保存叠加信息时，转化成10进制保存；将来查询时，再转化成2进制展示
        if(!CollectionUtils.isEmpty(works)){
            skuBoundsEntity.setWork(works.get(3) * 8 + works.get(2) *4 + works.get(1) * 2 + works.get(0) * 1);
        }
        this.save(skuBoundsEntity);

        // 3.2. 保存满减信息skuFullReduction
        SkuFullReductionEntity reductionEntity = new SkuFullReductionEntity();
        reductionEntity.setFullPrice(skuSaleVo.getFullPrice());
        reductionEntity.setReducePrice(skuSaleVo.getReducePrice());
        reductionEntity.setAddOther(skuSaleVo.getFullAndOther());
        reductionEntity.setSkuId(skuId);
        this.reductionDao.insert(reductionEntity);

        // 3.3. 保存打折信息skuLadder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setFullCount(skuSaleVo.getFullCount());
        skuLadderEntity.setDiscount(skuSaleVo.getDiscount());
        skuLadderEntity.setAddOther(skuSaleVo.getLadderAddOther());
        skuLadderEntity.setSkuId(skuId);
        this.ladderDao.insert(skuLadderEntity);

    }

    @Override
    public List<ItemSaleVO> queryItemSaleBySkuId(Long skuId) {
        List<ItemSaleVO> itemSaleVOS = new ArrayList<>();
        // 查询bounds
        SkuBoundsEntity skuBoundsEntity = this.getOne(new QueryWrapper<SkuBoundsEntity>().eq("sku_id", skuId));
        if (skuBoundsEntity != null) {
            ItemSaleVO itemSaleVO = new ItemSaleVO();
            itemSaleVO.setType("积分");
            itemSaleVO.setDesc("成长积分赠送" + skuBoundsEntity.getGrowBounds() + ",购物积分赠送" + skuBoundsEntity.getBuyBounds());
            itemSaleVOS.add(itemSaleVO);
        }

        // 查询满减信息
        SkuFullReductionEntity skuFullReductionEntity = this.reductionDao.selectOne(new QueryWrapper<SkuFullReductionEntity>().eq("sku_id", skuId));
        if (skuFullReductionEntity != null) {
            ItemSaleVO itemSaleVO = new ItemSaleVO();
            itemSaleVO.setType("满减");
            itemSaleVO.setDesc("满" + skuFullReductionEntity.getFullPrice() + "减" + skuFullReductionEntity.getReducePrice());
            itemSaleVOS.add(itemSaleVO);
        }

        // 查询打折信息
        SkuLadderEntity ladderEntity = this.ladderDao.selectOne(new QueryWrapper<SkuLadderEntity>().eq("sku_id", skuId));
        if (ladderEntity != null) {
            ItemSaleVO itemSaleVO = new ItemSaleVO();
            itemSaleVO.setType("打折");
            itemSaleVO.setDesc("满" + ladderEntity.getFullCount() + "件打" + ladderEntity.getDiscount().divide(new BigDecimal(10)) + "折");
            itemSaleVOS.add(itemSaleVO);
        }
        return itemSaleVOS;
    }

}