package cc.mrbird.febs.mq.msg;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.mq.feign.SystemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/19 22:12
 */
@Component
@RabbitmqForUse(value = "topic.opLog")
public class OpLogMsgConsume implements MsgInterface {

    @Autowired
    private SystemClient systemClient;

    @Override
    public Result consumeMsg(String msg) {
        return systemClient.saveForMq(msg);
    }
}
