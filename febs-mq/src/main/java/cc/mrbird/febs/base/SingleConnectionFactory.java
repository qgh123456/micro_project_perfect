package cc.mrbird.febs.base;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @ProjectName: rabbitmq_demo02
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/3/7 19:32
 */
public class SingleConnectionFactory {

    private SingleConnectionFactory() {
    }

    private static class SingleConnectionFactoryHolder{
        private static ConnectionFactory connectionFactory;
        static {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("47.98.136.239");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            connectionFactory.setVirtualHost("/baixuevirtual");
        }
    }

    public static ConnectionFactory getConnectionFactory(){
        return SingleConnectionFactoryHolder.connectionFactory;
    }

}
