package com.atguigu.gmall.cart.pojo;

import com.atguigu.gmall.pms.entity.SkuSaleAttrValueEntity;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {

    // 商品id
    private Long skuId;

    // 选中状态
    private Boolean check;

    // 标题
    private String title;

    // 图片
    private String image;

    // 销售属性
    private List<SkuSaleAttrValueEntity> saleAttrs;

    // 加入购物车时的价格
    private BigDecimal price;

    // 实时价格
    private BigDecimal currentPrice;

    // 购买数量
    private BigDecimal count;

    // 营销信息
    private List<ItemSaleVO> sales;

}
