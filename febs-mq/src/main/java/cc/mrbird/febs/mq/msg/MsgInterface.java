package cc.mrbird.febs.mq.msg;

import cc.mrbird.febs.common.entity.Result;

public interface MsgInterface {

    Result consumeMsg(String msg);
}
