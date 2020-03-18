package com.atguigu.gmall.item.vo;

import com.atguigu.gmall.pms.entity.SkuImagesEntity;
import com.atguigu.gmall.pms.entity.SkuSaleAttrValueEntity;
import com.atguigu.gmall.pms.vo.ItemGroupVO;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品详情页
 */

@Data
public class ItemVo {

    //分类
    private Long categoryId;
    private String categoryName;

    // 品牌
    private Long brandId;
    private String brandName;

    // spu信息
    private Long spuId;
    private String spuName;

    // sku相关信息
    private Long skuId;
    private String skuTitle;
    private String skuSubTitle;
    private BigDecimal price;
    private BigDecimal weight;

    // 营销信息
    private List<ItemSaleVO> sales;

    // 是否有货
    private Boolean store = false;

    // 需要sku所属spu下的所有sku的营销信息
    private List<SkuSaleAttrValueEntity> saleAttrs;

    // sku的图片信息
    private List<SkuImagesEntity> images;

    // 海报信息
    private List<String> desc;

    // 分组及组下的规格参数
    private List<ItemGroupVO> groups;

}
