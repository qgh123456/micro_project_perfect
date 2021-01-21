package cc.mrbird.febs.common.api;

import cc.mrbird.febs.common.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface SystemApi {

    @PostMapping("/system/sysoperlog/saveForMq")
    public Result<Object> saveForMq(@RequestParam("messageData") String messageData);
}
