package com.atguigu.gmall.search.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;


@Data
@Document(indexName = "goods", type = "info", shards = 3, replicas = 2)
public class GoodsVo {
    /**
     * 商品列表
     */
    @ApiModelProperty(name = "skuId",value = "skuId")
    @Id
    @Field(type = FieldType.Long, index = false)
    private Long skuId;

    @ApiModelProperty(name = "title",value = "标题")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @ApiModelProperty(name = "price",value = "价格")
    @Field(type = FieldType.Double)
    private Double price;

    @ApiModelProperty(name = "pic",value = "默认图片")
    @Field(type = FieldType.Keyword, index = false)
    private String pic;

    /**
     * 排序及筛选字段
     */
    @ApiModelProperty(name = "sale",value = "销量")
    @Field(type = FieldType.Integer)
    private Integer sale;

    @ApiModelProperty(name = "createTime",value = "创建时间")
    @Field(type = FieldType.Date)
    private Date createTime;

    @ApiModelProperty(name = "store",value = "是否有货")
    @Field(type = FieldType.Boolean)
    private Boolean store = false;

    @ApiModelProperty(name = "brandId",value = "品牌id")
    @Field(type = FieldType.Long)
    private Long brandId;

    @ApiModelProperty(name = "name",value = "品牌名")
    @Field(type = FieldType.Keyword)
    private String name;

    @ApiModelProperty(name = "catId",value = "分类id")
    @Field(type = FieldType.Long)
    private Long categoryId;

    @ApiModelProperty(name = "name",value = "分类名称")
    @Field(type = FieldType.Keyword)
    private String categoryName;

    @ApiModelProperty(name = "attrs",value = "嵌套类型")
    @Field(type = FieldType.Nested)
    private List<SearchAttrVo> attrs;


}
