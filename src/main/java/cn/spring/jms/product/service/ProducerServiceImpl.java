package cn.spring.jms.product.service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("cn.spring.jms.product.service.ProducerServiceImpl")  
public class ProducerServiceImpl {   
    
    private JmsTemplate jmsTemplate;   
    
    @Autowired  
    @Qualifier("responseQueue")   
    private Destination responseDestination;   
       
    public void sendMessage(Destination destination, final String message) {   
        System.out.println("---------------生产者发送消息-----------------");   
        System.out.println("---------------生产者发了一个消息：" + message);   
        jmsTemplate.send(destination, new MessageCreator() {   
            public Message createMessage(Session session) throws JMSException {   
            	return session.createTextMessage(message);   
            }   
        });   
    }    
  
    public JmsTemplate getJmsTemplate() {   
        return jmsTemplate;   
    }    
  
    @Resource  
    public void setJmsTemplate(JmsTemplate jmsTemplate) {   
        this.jmsTemplate = jmsTemplate;   
    }   
    
}  