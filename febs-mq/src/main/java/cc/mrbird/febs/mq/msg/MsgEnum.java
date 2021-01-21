package cc.mrbird.febs.mq.msg;

import java.util.HashMap;
import java.util.Map;

public enum MsgEnum {

    MS033("topic.opLog", "");

    private String msgid;
    private String clazz;

    public static Map<String, String> getAllClazz() {
        Map<String, String> map = new HashMap<String, String>();
        for (MsgEnum msgEnum : MsgEnum.values()) {
            map.put(msgEnum.getMsgid(), msgEnum.getClazz());
        }
        return map;
    }


    MsgEnum(String msgid, String clazz) {
        this.msgid = msgid;
        this.clazz = clazz;
    }


    public String getMsgid() {
        return msgid;
    }


    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }


    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
