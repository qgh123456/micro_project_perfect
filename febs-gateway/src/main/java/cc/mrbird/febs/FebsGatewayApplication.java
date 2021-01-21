package cc.mrbird.febs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/4 11:17
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class FebsGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FebsGatewayApplication.class, args);
    }
}
