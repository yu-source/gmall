package com.atguigu.gmall.sms.vo;

import lombok.Data;

/**
 * 所有的优惠信息
 */

@Data
public class ItemSaleVO {

    // 促销类型：积分 打折 满减
    private String type;

    // 营销的详细信息
    private String desc;

}
