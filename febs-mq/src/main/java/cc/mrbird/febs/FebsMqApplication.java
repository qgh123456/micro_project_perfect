package cc.mrbird.febs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: micro_project_perfect
 * @Description: mq的消费端
 * @Author: qiguohui
 * @Date: 2021/1/19 15:32
 */
@EnableFeignClients // 开启feign
@EnableDiscoveryClient
@SpringBootApplication
public class FebsMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsMqApplication.class, args);
    }

}
