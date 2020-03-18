package com.atguigu.gmall.pay.interceptor;

import com.atguigu.core.bean.UserInfo;
import com.atguigu.core.utils.CookieUtils;
import com.atguigu.core.utils.JwtUtils;
import com.atguigu.gmall.pay.config.JwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@EnableConfigurationProperties({JwtProperties.class})
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    // public static Long USER_ID;  无法保证线程安全

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 目的：获取登录状态，传给后续业务逻辑：controller service dao
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取cookie中的信息：token userKey
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());

        // 这里不需要userKey，操作订单必须是登录状态

        // 2.判断token是否为空
        Long userId = null;
        if(StringUtils.isNotBlank(token)){
            // 4.解析token，正常解析获取userId
            Map<String, Object> map = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            userId = Long.valueOf(map.get("userId").toString());
        }

        // 3.把userId或者是userKey传递给后续的业务逻辑
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        THREAD_LOCAL.set(userInfo);

        return true;
    }

    /**
     * 这里封装一个get方法获取载荷数据
     * 如果把THREAD_LOCAL声明为公开，会不安全
     */
    public static UserInfo getUserInfo(){
        return THREAD_LOCAL.get();
    }

    /**
     * tomcat线程池，请求处理完成后，线程并没有结束
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 手动释放线程变量
        THREAD_LOCAL.remove();
    }
}
