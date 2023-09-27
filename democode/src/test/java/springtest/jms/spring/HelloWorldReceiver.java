package springtest.jms.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * @author Vin lan
 * @className HelloWorldReceiver
 * @description
 * @createTime 2021-10-25  17:06
 **/
public class HelloWorldReceiver {
    public static void main(String[] args) throws JMSException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("queueDestination");
        // 只能接收一次消息
        TextMessage message = (TextMessage) jmsTemplate.receive(destination);
        System.out.println("received msg is:" + message.getText());
    }
}
