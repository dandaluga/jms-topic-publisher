package com.daluga.jms.topic.publisher;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.*;

@SpringBootApplication
public class JmsTopicPublisherApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsTopicPublisherApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JmsTopicPublisherApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.debug("JmsTopicPublisherApplication Has Started!");

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("DD00865.TOPIC");

        MessageProducer messageProducer = session.createProducer(topic);

        TextMessage textMessage = session.createTextMessage("YoYoMa");

        messageProducer.send(textMessage);

        connection.close();

        LOGGER.debug("JmsTopicPublisherApplication Has Ended!");
    }
}
