package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SkuInfoEntity;
import com.atguigu.gmall.pms.entity.SkuSaleAttrValueEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuInfoVo extends SkuInfoEntity {

    private List<String> images;

    private List<SkuSaleAttrValueEntity> saleAttrs;

    /**
     * 积分优惠信息
     */
    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private List<Integer> work;

    /**
     * 打折信息
     */
    private Integer fullCount;
    private BigDecimal discount;
    private Integer ladderAddOther;

    /**
     * 满减信息
     */
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer fullAndOther;



}
