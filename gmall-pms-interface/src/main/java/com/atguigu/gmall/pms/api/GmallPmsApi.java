package com.atguigu.gmall.pms.api;

import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GmallPmsApi {

    /**
     * 分页查询spu接口
     */
    @PostMapping("pms/spuinfo/page")
    public Resp<List<SpuInfoEntity>> querySpuPage(@RequestBody QueryCondition queryCondition);

    /**
     * 根据spuId查询spu下的sku接口
     */
    @GetMapping("pms/skuinfo/{spuId}")
    public Resp<List<SkuInfoEntity>> querySkusBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 根据brandId查询品牌（品牌的名称）
     */
    @GetMapping("pms/brand/info/{brandId}")
    public Resp<BrandEntity> queryBrandById(@PathVariable("brandId") Long brandId);

    /**
     * 根据categoryId查询分类（分类名称）
     */
    @GetMapping("pms/category/info/{catId}")
    public Resp<CategoryEntity> queryCategoryById(@PathVariable("catId") Long catId);

    /**
     *  根据分类等级或者父id查询分类
     */
    @GetMapping("pms/category")
    public Resp<List<CategoryEntity>> queryCategoriesByLevelOrPid(@RequestParam(value = "level", defaultValue = "0") Integer level,
                                                                  @RequestParam(value = "parentCid", required = false) Long pid);

    /**
     * 父id查询二级分类及其子分类
     */

    /**
     * 根据spuId查询检索的规格参数及值
     */
    @GetMapping("pms/productattrvalue/{spuId}")
    public Resp<List<ProductAttrValueEntity>> queryAttrValueBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 根据skuId查询sku
     */
    @GetMapping("pms/skuinfo/info/{skuId}")
    public Resp<SkuInfoEntity> querySkuById(@PathVariable("skuId") Long skuId);

    /**
     * 根据spuId查询spu
     */
    @GetMapping("pms/spuinfo/info/{id}")
    public Resp<SpuInfoEntity> querySpuById(@PathVariable("id") Long id);

    /**
     *  根据spuId查询spu描述
     */
    @GetMapping("pms/spuinfodesc/info/{spuId}")
    public Resp<SpuInfoDescEntity> querySpuDescBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 根据spuId查询营销信息
     */

    /**
     * 根据skuId查询营销信息
     */
    @GetMapping("pms/skusaleattrvalue/skuId/{skuId}")
    public Resp<List<SkuSaleAttrValueEntity>> querySaleAttrBySkuId(@PathVariable("skuId")Long skuId );
}
