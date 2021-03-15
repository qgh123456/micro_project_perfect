package cc.mrbird.febs.server.test;

import cc.mrbird.febs.server.test.feign.IHelloServiceClient;
import cc.mrbird.febs.server.test.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2020/12/31 10:23
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private IHelloService helloService;
    @Autowired
    private IHelloServiceClient helloServiceClient;

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1(){
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2(){
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        log.info("Feign调用febs-server-system的/hello服务...");
        return this.helloService.hello(name);
    }

    @GetMapping("hello2")
    public String hello2(String name) {
        log.info("Feign调用febs-server-system的/hello服务...");
        return this.helloServiceClient.hello(name);
    }

}
