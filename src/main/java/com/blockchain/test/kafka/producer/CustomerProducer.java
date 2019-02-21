package com.blockchain.test.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class CustomerProducer {

	public static void main(String[] args) {
		//配置信息
		Properties props = new Properties();
		//kafka集群
		props.put("bootstrap.servers", "10.1.23.64:9092,10.1.23.75:9092,10.1.23.63:9092");
		//ack应答级别
		props.put("acks", "all");
		//重试次数
		props.put("retries", 0);
		//提交超时
		props.put("delivery.timeout.ms", 3000000);
		//批量大小
		props.put("batch.size", 16384);
		//提交延迟
		props.put("linger.ms", 1);
		//缓存
		props.put("buffer.memory", 33554432);
		//序列化类
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//自定义分区 PARTITIONER_CLASS_CONFIG = "partitioner.class";
		props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,CustomerPartition.class);

		//创建生产者
		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>("meng", Integer.toString(i), Integer.toString(i)), (metadata, exception) -> {
				if (exception == null) {
					System.out.println(metadata.partition() + "--" + metadata.offset());
				}
			});
		//关闭资源
		producer.close();
	}
}
