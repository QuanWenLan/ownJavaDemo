package springtest.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Vin lan
 * @className Receiver
 * @description
 * @createTime 2021-10-25  16:38
 **/
public class Receiver {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection("admin", "admin");
        connection.start();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("my-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        int i = 0;
        while (i < 3) {
            i++;
            TextMessage message = (TextMessage) consumer.receive();
            session.commit();
            System.out.println("收到消息：" + message);
        }
        session.close();
        connection.close();
    }
}
