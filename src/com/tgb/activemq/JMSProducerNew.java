package com.tgb.activemq;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息的生产者（发送者）
 * 
 * @author liang
 *
 */
public class JMSProducerNew {

	// 默认连接用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	// 默认连接密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	// 默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	// 发送的消息数量
	private static final int SENDNUM = 10;

	public static void main(String[] args) {
		// 连接工厂
		ConnectionFactory connectionFactory;
		// 连接
		Connection connection = null;
		// 会话 接受或者发送消息的线程
		Session session;
		// 消息的目的地1
		Destination destination;
		// 消息的目的地2
		Destination destination2;
		// 消息生产者
		MessageProducer messageProducer;
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(JMSProducerNew.USERNAME, JMSProducerNew.PASSWORD,
				JMSProducerNew.BROKEURL);

		try {
			// 通过连接工厂获取连接
			connection = connectionFactory.createConnection();
			// 启动连接
			 connection.start();
			// 创建session  事务是否开启
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个名称为HelloWorld的消息队列1
			destination = session.createQueue("HelloWorld3");
			// 创建一个名称为HelloWorld的消息队列1
			destination2 = session.createQueue("HelloWorld4");
			// 创建消息生产者
			messageProducer = session.createProducer(null);
			Message createTextMessage = null;
			for (int i = 1; i <= 10; i++) {
				createTextMessage = session.createTextMessage("我是消息队列" + i);
				if (i / 2 == 0) {
					// 发送消息
					messageProducer.send(destination, createTextMessage);
				} else {
					// 发送消息
					messageProducer.send(destination2, createTextMessage);
				}
				TimeUnit.SECONDS.sleep(2);
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息
	 * 
	 * @param session
	 * @param messageProducer
	 *            消息生产者
	 * @throws Exception
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
		for (int i = 0; i < JMSProducerNew.SENDNUM; i++) {
			// 创建一条文本消息
			TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
			System.out.println("发送消息：Activemq 发送消息" + i);
			// 通过消息生产者发出消息
			messageProducer.send(message);
		}

	}
}