package com.atguigu.gmall.order.vo;

import com.atguigu.gmall.ums.entity.MemberReceiveAddressEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVO {

    // 收货地址列表
    private List<MemberReceiveAddressEntity> addresses;

    // 送货清单
//    private List<>

}
