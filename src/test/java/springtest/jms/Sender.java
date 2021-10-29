package springtest.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Vin lan
 * @className Sender
 * @description
 * @createTime 2021-10-25  16:12
 **/
public class Sender {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection("admin", "admin");
//        connection.start();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("my-queue");
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("大家好这是个测试！");
            Thread.sleep(1000);
            producer.send(textMessage);
        }

        session.commit();
        session.close();
        connection.close();
    }
}
