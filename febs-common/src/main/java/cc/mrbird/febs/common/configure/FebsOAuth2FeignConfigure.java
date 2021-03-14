package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.entity.FebsConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @ProjectName: micro_project_perfect
 * @Description: 解决Feign在调用远程服务的时候，并不会帮我们把原HTTP请求头部的内容也携带
 * @Author: qiguohui
 * @Date: 2021/1/4 15:05
 */
public class FebsOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(FebsConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(FebsConstant.ZUUL_TOKEN_HEADER, zuulToken);
            // 获取用户的详情
            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                // 往请求头中添加token
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}
