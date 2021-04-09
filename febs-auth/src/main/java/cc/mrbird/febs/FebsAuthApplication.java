package cc.mrbird.febs;

import cc.mrbird.febs.common.annotation.EnableFebsAuthExceptionHandler;
import cc.mrbird.febs.common.annotation.EnableFebsLettuceRedis;
import cc.mrbird.febs.common.annotation.EnableFebsServerProtect;
import cc.mrbird.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2020/12/29 13:49
 */
//@EnableDiscoveryClient #nacos 不需要这个注解也可以
@SpringBootApplication
//@EnableFebsAuthExceptionHandler
//@EnableFebsServerProtect
@FebsCloudApplication
@EnableFebsLettuceRedis
@MapperScan("cc.mrbird.febs.auth.mapper")
public class FebsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsAuthApplication.class, args);
    }

}