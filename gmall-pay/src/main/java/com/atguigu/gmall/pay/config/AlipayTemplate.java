package com.atguigu.gmall.pay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gmall.pay.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2016101500692147";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDd/oV3AjbQhLMQOBoFiKkIxZw/C+0pfsrXuh2Fiy/nWd1nUvLu2TagTw+O+dZ6TMgM9YrdKsL+TeGXiyW7vaQtzoRo4GCG7EpGXfEjobb6qxvU5RX53O5bNcWIL48JSEN5fIqJ651ejipZ31CGjPOuBSZTGquxYWI2BdOGaQPgYpa6cop7Qp9CpnW8qEpW5dPlZvq48pkcfuGiRc107n9EmQd+az/VmGjRb++2KLS0GrA0DU4MhjqlPT2AM5kM6tK1Ir8m40+cf774VHPPcNrYUX7qzMbHrzcNMeNWvUO9MLGSMB25IZgp/ikdWMsxthFKIpm00TyVSRkulcuENqEXAgMBAAECggEAKTaigpinooiwUbeIpTRc5WMCgfXt3sFKWUiVSICxsQrO/uq4bT1oVdS3t7yMZAIzNay0TiEtOFcvJZpQtA0eGN75WBsn8ISXuX6hURMpzFnN/fVV4iBxI//Un1btADUpSbmkYryaZ76PAEZkRcaPSuRr3Avcij7hI9RrE84VSSfL8+hytXBApTvbN9/r14Xfiln+NJ/4Ot3lTxmA+XoitbVgn2MXXh/wgANwPMExDQ70CHpJLRQSths2hE/Odl1cmWcZQhgBVet+S9zJxtyIlkeyMiSykiKmpVRB5cG/RFcOgh4m5OuHZ7ae6QaxW9hIoWBPU7TksHfu+aI04QSA4QKBgQD1f7LR3Allmr0HO/2q7wWcFa8R3le+uRw4Tjr5uqFxidYZpwsPEC/Lb63jIxFLiDpb59yz0w3GYNE0RQxHXDrAn5ukFGV8Nv59F2hmBOfuiiFK9nB4ohkvibl1aKo5wXyocK9NgL+GLiQfihtYef/iDrifydI4Wh4AZdXLfASQRwKBgQDnfXBdSZblVhiCxTFJuCwm490ugY7HZF8k23aIK/BC0WuVOT3AmX2ZndncmR79T/tShWnRozcFx5x6sQls3vWnA4tiVRmp8sDmffzfcj4HycuehzRgWbLhaNswWBWAD48HUVXekYB3ipGqerx2LZEvbbkdqlrkY6MfpwoNTcogsQKBgQCLFHUx/v4z2Z4NYycql48OBhrQfK4pFqWuIGO45spfkD8C+WeCeSEal4Jjk5JQwDSPrKOUHJVJNcBPZG64Xr/KMKH0KrnzzRQPuhsi8NSUhxylY0gEj5DFJbkzksAdSEhfBLfLqIzYb3t3gr5LOZKFduGRZLz7v9IPV6HvTasoMQKBgE4nTX5RT/6ReKj9xkwrP8zJdW00FG5A9tZMudHzc6Q7a+TvdGfH6l2oq1sM3yeKj0EnBbjfL75ly/CoQJRfGfSO/8ntDUMRUAsBUhRmT4b1nCcjYo18+yWa7xV9R5T6q8E+tozz9rllMRSrZ2FGtROcKblYQGOHOBWBoaUoNCuhAoGBALskHNdD3MNQyJEPq5gUXzHXogfhbbugB2GjtoO5rDwiA0p7+34aJWCfxCzECwXE20b8e+oefoJLBnXsvkke1BAJPMb1S8AhfvOze3VLNUB0nwygb2WsAc7Vi3M04SxbZ9fI6/piE9fZgcu02kooTJHEwf9lel8fRdAmb2avuvto";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyfmOgC9X8WTTeDfEvJ/kXV53ZMu9Xh7I9SxaH6o42Y3N3hpxq4mn4bTo+dmfphWaa32FKnT4AARiEpvScIJTJETW5nxNzLRZ7ePKmAErllRTLGQ4osvGVNN4CgAnZVxCldPQpl50er7Tomg3jGQZjVZYMLeWpZ8kQnWkwyMEiYg9kj9vbVWzqWGUBHxuKZvbee1IHC06jqqYQ9G/bA6C2XXeA1RRNo+NwBy8JvZ/U3k8dRf8G2U2TWOiv2YxulG/Df8LY/bHv4zqx/owwmQdVl4OZ5BUKmGu9CkBC8D+JaNGw6176O7GlW0c2JJzNDM3m7TLasIbvPWVcH62rZRulQIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url;

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
