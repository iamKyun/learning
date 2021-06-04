package com.iamkyun.spb.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ActiveMQDemo {

    public static void main(String[] args) throws Exception {
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //6、使用会话对象创建生产者对象
        MessageConsumer consumer = session.createConsumer(queue);
        //7、向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(message -> {
            // TODO Auto-generated method stub
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        Connection rconnection = connectionFactory.createConnection();
        //3、开启连接
        rconnection.start();
        //4、使用连接对象创建会话（session）对象
        Session rsession = rconnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue rqueue = rsession.createQueue("test-queue");
        //6、使用会话对象创建生产者对象
        MessageProducer producer = rsession.createProducer(rqueue);


        for (int i = 0; i < 10; i++) {
            //7、使用会话对象创建一个消息对象
            TextMessage textMessage = rsession.createTextMessage("hello!test-queue" + i);
            //8、发送消息
            producer.send(textMessage);
            Thread.sleep(3 * 1000);
        }

        //9、关闭资源
        producer.close();
        rsession.close();
        rconnection.close();



        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

}
