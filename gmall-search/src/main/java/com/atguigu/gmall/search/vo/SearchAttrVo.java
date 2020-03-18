package com.atguigu.gmall.search.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class SearchAttrVo {
    @ApiModelProperty(name = "attrId",value = "属性id")
    @Field(type = FieldType.Long)
    private Long attrId;

    @ApiModelProperty(name = "attrName",value = "属性名")
    @Field(type = FieldType.Keyword)
    private String attrName;

    @ApiModelProperty(name = "attrvalue",value = "可选值列表[用逗号分隔]")
    @Field(type = FieldType.Keyword)
    private String attrValue;
}
