package com.atguigu.gmall.search.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponseAttrVo {

    /**
     * 属性的ID
     */
    private Long attrId;
    /**
     * 属性的名称
     */
    private String attrName;
    /**
     * 属性值的集合
     */
    private List<String> attrValues;

}
