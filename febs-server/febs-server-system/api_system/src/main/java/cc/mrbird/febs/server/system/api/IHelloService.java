package cc.mrbird.febs.server.system.api;

import org.springframework.web.bind.annotation.RequestParam;

public interface IHelloService {

    String hello(@RequestParam("name") String name);
}
