package cc.mrbird.febs.base;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: rabbitmq_demo02
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/3/7 19:30
 */
public abstract class ConsumeBase {

    public void consume(String queueName) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = SingleConnectionFactory.getConnectionFactory();
        // 修建高速公路
        Connection connection = connectionFactory.newConnection();
        // 画一个双向车道
        Channel channel = connection.createChannel();
        this.consumeContent(queueName,channel);
    }

    public abstract void consumeContent(String queueName,Channel channel) throws IOException;


}
