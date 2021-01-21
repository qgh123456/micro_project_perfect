package cc.mrbird.febs.mq.msg;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.utils.SpringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/19 22:02
 */
@Component
public class MsgContext {

    private Map<String,Class<MsgInterface>> map = new HashMap<>();

    {
        List<Class> classes = ClassScan.scanClass("cc.mrbird.febs.mq.msg", RabbitmqForUse.class);
        for (Class var : classes){
            boolean annotationPresent = var.isAnnotationPresent(RabbitmqForUse.class);
            if(annotationPresent){
                RabbitmqForUse rabbitmqForUse = (RabbitmqForUse)var.getAnnotation(RabbitmqForUse.class);
                String value = rabbitmqForUse.value();
                map.put(value,var);
            }
        }
    }

    /**
     * 消费
     * @param queueName
     * @param msg
     * @return
     */
    Result consumeMsg(String queueName,String msg){
        Class<MsgInterface> clazz = map.get(queueName);
        MsgInterface msgService = SpringUtils.getBean(clazz);
        return msgService.consumeMsg(msg);
    }

    public static MsgInterface getInstance(String msgId){
        MsgInterface inter=null;
        Map<String, String> allClazz = MsgEnum.getAllClazz();
        String clazz = allClazz.get(msgId);
        if (msgId!=null&&msgId.trim().length() > 0) {
            try {
                try {
                    inter = (MsgInterface) Class.forName(clazz).newInstance();//调用无参构造器创建实例
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return inter;
    }
}
