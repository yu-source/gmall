package com.atguigu.gmall.auth.service.impl;

import com.atguigu.core.bean.Resp;
import com.atguigu.core.exception.MemberException;
import com.atguigu.core.utils.JwtUtils;
import com.atguigu.gmall.auth.config.JwtProperties;
import com.atguigu.gmall.auth.feign.GmallUmsClient;
import com.atguigu.gmall.auth.service.AuthService;
import com.atguigu.gmall.ums.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableConfigurationProperties({JwtProperties.class})
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private GmallUmsClient umsClient;

    @Override
    public String accredit(String username, String password) {
        // 1.调用ums的用户查询接口
        Resp<MemberEntity> memberEntityResp = this.umsClient.queryUser(username, password);
        MemberEntity memberEntity = memberEntityResp.getData();
        // 2.不存在直接返回
        if (memberEntity == null) {
            throw new MemberException("用户名或者密码输入有误！");
        }
        // 3.制作jwt
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", memberEntity.getId());
            map.put("nickname", memberEntity.getNickname());
            return JwtUtils.generateToken(map, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExprieTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
