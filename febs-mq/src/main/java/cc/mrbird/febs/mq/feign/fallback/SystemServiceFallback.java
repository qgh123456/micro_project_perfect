package cc.mrbird.febs.mq.feign.fallback;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.mq.feign.SysOperLogClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/19 18:57
 */
@Slf4j
@Component
public class SystemServiceFallback implements FallbackFactory<SysOperLogClient> {
    @Override
    public SysOperLogClient create(Throwable throwable) {
        return new SysOperLogClient() {

            @Override
            public Result<Object> saveForMq(String messageData) {
                log.error("调用febs-server-system服务出错", throwable);
                // 返回调用出错
                return Result.error().data("调用出错");
            }
        };
    }
}
