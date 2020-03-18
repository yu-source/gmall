package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许那些域名跨域访问，不要写*（代表所有域名，但不能携带cookie）
        configuration.addAllowedOrigin("http://localhost:1000");
        configuration.addAllowedOrigin("http://127.0.0.1:1000");
        // 允许所有方法跨域访问
        configuration.addAllowedMethod("*");
        // 允许所有头信息跨域访问
        configuration.addAllowedHeader("*");
        // 允许携带cookie
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsWebFilter(corsConfigurationSource);
    }
}
