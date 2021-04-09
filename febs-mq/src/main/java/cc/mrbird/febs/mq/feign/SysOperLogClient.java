package cc.mrbird.febs.mq.feign;

import cc.mrbird.febs.common.entity.FebsServerConstant;
import cc.mrbird.febs.mq.feign.fallback.SystemServiceFallback;
import cc.mrbird.febs.server.system.api.ISysOperLogService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM,contextId = "sysOperLogClient", fallbackFactory = SystemServiceFallback.class)
public interface SysOperLogClient extends ISysOperLogService {


}
