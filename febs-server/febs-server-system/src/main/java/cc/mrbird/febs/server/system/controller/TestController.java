package cc.mrbird.febs.server.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author MrBird
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("info")
    public String test(){
        return "febs-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        log.info("/hello服务被调用");
        if(name.equals("xiaoming")){
            throw new RuntimeException("xxx");
        }
        if(name.equals("xiaohong")){
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello" + name;
    }
}
