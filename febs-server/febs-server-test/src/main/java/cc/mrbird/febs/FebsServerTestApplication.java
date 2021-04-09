package cc.mrbird.febs;

import cc.mrbird.febs.common.annotation.EnableFebsAuthExceptionHandler;
import cc.mrbird.febs.common.annotation.EnableFebsOauth2FeignClient;
import cc.mrbird.febs.common.annotation.EnableFebsServerProtect;
import cc.mrbird.febs.common.annotation.FebsCloudApplication;
import cc.mrbird.febs.common.handler.FebsAccessDeniedHandler;
import cc.mrbird.febs.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2020/12/31 10:19
 */
@EnableFeignClients // 开启feign
//@EnableDiscoveryClient #nacos不需要
@SpringBootApplication
@FebsCloudApplication
// 表示开启Spring Cloud Security权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FebsServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerTestApplication.class, args);
    }

}
