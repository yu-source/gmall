package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import lombok.Data;

import java.util.List;

/**
 * 基本属性分组及组下的规格参数
 */

@Data
public class ItemGroupVO {

    private Long groupId;

    // 分组的名字
    private String groupName;

    // 基本属性名及值
    private List<ProductAttrValueEntity> baseAttrValues;

}
