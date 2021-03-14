package cc.mrbird.febs.auth.config;

import cc.mrbird.febs.auth.properties.FebsAuthProperties;
import cc.mrbird.febs.common.handler.FebsAccessDeniedHandler;
import cc.mrbird.febs.common.handler.FebsAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @ProjectName: micro_project_perfect
 * @Description:  资源服务器 3
 * @Author: qiguohui
 * @Date: 2020/12/29 10:55
 */
@Configuration
// 声明为一个OAuth2的资源服务器
@EnableResourceServer
public class FebsResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private FebsAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private FebsAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private FebsAuthProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                // 匹配所有的请求
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                // 对请求中annoUrls免认证
                .antMatchers(anonUrls).permitAll()
                // 其他的都认证
                .antMatchers("/**").authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
