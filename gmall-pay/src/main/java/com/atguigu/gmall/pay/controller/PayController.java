package com.atguigu.gmall.pay.controller;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pay.vo.PayAsyncVo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * 支付成功后的回调方法
     */
    @PostMapping("alipay/success")
    public Resp<Object> paySuccess(PayAsyncVo payAsyncVo){
        // 发送消息给订单，修改订单状态
        this.amqpTemplate.convertAndSend("ORDER-EXCHANGE", "order.pay", payAsyncVo.getOut_trade_no());
        return Resp.ok(null);
    }


}
