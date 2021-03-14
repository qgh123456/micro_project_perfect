package cc.mrbird.febs.server.system.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}
