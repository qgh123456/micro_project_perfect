package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.interceptor.FebsServerProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: micro_project_perfect
 * @Description: 添加进入每个微服务的过滤
 * @Author: qiguohui
 * @Date: 2021/1/4 19:19
 */
public class FebsServerProtectConfigure implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private HandlerInterceptor febsServerProtectInterceptor;

    @Bean
    public HandlerInterceptor febsServerProtectInterceptor() {
        return new FebsServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.febsServerProtectInterceptor);
    }
}
