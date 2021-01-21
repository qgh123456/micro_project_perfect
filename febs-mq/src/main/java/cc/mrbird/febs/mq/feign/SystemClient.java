package cc.mrbird.febs.mq.feign;

import cc.mrbird.febs.common.api.SystemApi;
import cc.mrbird.febs.common.entity.FebsServerConstant;
import cc.mrbird.febs.mq.feign.fallback.SystemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM,contextId = "systemClient", fallbackFactory = SystemServiceFallback.class)
public interface SystemClient extends SystemApi{


}
