package cc.mrbird.febs.server.system.api;

import cc.mrbird.febs.common.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ISysOperLogService {

    @PostMapping("/sysoperlog/saveOpLog")
    Result saveForMq(@RequestParam("messageData") String messageData);
}
