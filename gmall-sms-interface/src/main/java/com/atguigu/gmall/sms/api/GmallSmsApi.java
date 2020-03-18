package com.atguigu.gmall.sms.api;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GmallSmsApi {

    /**
     * 大保存之保存营销信息
     */
    @PostMapping("sms/skubounds")
    public Resp<Object> saveSkuSales(@RequestBody SkuSaleVo skuSaleVo);

    /**
     * 查询sku的营销信息
     */
    @GetMapping("sms/skubounds/{skuId}")
    public Resp<List<ItemSaleVO>> queryItemSaleBySkuId(@PathVariable("skuId")Long skuId );

}
