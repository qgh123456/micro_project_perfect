package cc.mrbird.febs;

import cc.mrbird.febs.common.annotation.EnableFebsAuthExceptionHandler;
import cc.mrbird.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2020/12/30 17:47
 */
@EnableDiscoveryClient
@SpringBootApplication
@FebsCloudApplication
@EnableFebsAuthExceptionHandler
//表示开启Spring Cloud Security权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
@MapperScan("cc.mrbird.febs.server.system.mapper")
public class FebsServerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FebsServerSystemApplication.class, args);
    }
}
