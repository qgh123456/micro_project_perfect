package cc.mrbird.febs.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 标记服务端标识
@SpringBootApplication
public class FebsRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsRegisterApplication.class, args);
    }

}
