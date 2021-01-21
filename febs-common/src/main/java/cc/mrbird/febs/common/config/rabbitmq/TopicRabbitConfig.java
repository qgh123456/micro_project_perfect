package cc.mrbird.febs.common.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: rabbitmq_demo1
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/18 13:43
 */
@Configuration
public class TopicRabbitConfig {

    //绑定键
    public final static String opLog = "topic.opLog";

    @Bean
    public Queue opLogQueue() {
        return new Queue(TopicRabbitConfig.opLog);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicOpLogExchange");
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(opLogQueue()).to(exchange()).with(opLog);
    }

}
