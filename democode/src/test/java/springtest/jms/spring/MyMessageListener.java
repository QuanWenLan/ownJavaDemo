package springtest.jms.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Vin lan
 * @className MyMessageListener
 * @description
 * @createTime 2021-10-25  17:14
 **/
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
       TextMessage msg = (TextMessage) message;
       try {
           System.out.println(this.getClass().getSimpleName() + " " + msg.getText());
       } catch (JMSException e) {
           e.printStackTrace();
       }
    }
}
