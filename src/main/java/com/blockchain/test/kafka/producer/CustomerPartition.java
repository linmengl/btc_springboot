package com.blockchain.test.kafka.producer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomerPartition implements Partitioner {
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		return 0;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> configs) {
		//System.out.println(JSON.toJSON(configs));
	}
}
