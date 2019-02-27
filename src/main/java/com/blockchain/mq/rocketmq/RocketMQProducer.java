package com.blockchain.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class RocketMQProducer {
	public static void main(String[] args) {
		DefaultMQProducer producer = new DefaultMQProducer("myProducerGroup");
		producer.setNamesrvAddr("10.1.23.64:9876;10.1.23.75:9876");
		try {
			producer.start();
			for (int i = 0; i < 100; i++) {
				Message message = new Message("order", ("order" + i).getBytes());
				SendResult send = producer.send(message);
				System.out.println(send);
				Thread.sleep(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
