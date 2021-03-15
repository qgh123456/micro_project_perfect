package cc.mrbird.febs.server.test.feign;

import cc.mrbird.febs.common.entity.FebsServerConstant;
import cc.mrbird.febs.server.system.api.IHelloService;
import cc.mrbird.febs.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM,contextId = "helloServiceClient2", fallbackFactory = HelloServiceFallback.class)
public interface IHelloServiceClient extends IHelloService {
}
