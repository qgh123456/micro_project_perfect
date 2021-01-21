package cc.mrbird.febs.mq.config;

import cc.mrbird.febs.mq.msg.ClassScan;
import cc.mrbird.febs.mq.msg.RabbitmqForUse;
import cc.mrbird.febs.mq.receiver.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @ProjectName: rabbitmq_demo1
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/18 19:16
 */

@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private MyAckReceiver myAckReceiver;//消息接收处理类

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息

        List<Class> classes = ClassScan.scanClass("cc.mrbird.febs.mq.msg", RabbitmqForUse.class);
        StringBuilder stringBuilder = new StringBuilder();
        classes.forEach(var ->{
            boolean annotationPresent = var.isAnnotationPresent(RabbitmqForUse.class);
            if(annotationPresent){
                RabbitmqForUse annotation = (RabbitmqForUse)var.getAnnotation(RabbitmqForUse.class);
                stringBuilder.append(annotation.value());
                stringBuilder.append(",");
            }
        });
        String queueNames = stringBuilder.substring(0,stringBuilder.length() - 1);
        //设置一个队列
        container.setQueueNames(queueNames);
//        container.setQueueNames("topic.opLog");
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");


        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));
        container.setMessageListener(myAckReceiver);

        return container;
    }


}
