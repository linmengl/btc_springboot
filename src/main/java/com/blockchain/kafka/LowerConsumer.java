package com.blockchain.kafka;

import org.apache.kafka.clients.consumer.Consumer;

import java.util.ArrayList;
import java.util.List;

public class LowerConsumer {

    public static void main(String[] args) {
        //定义相关参数
        ArrayList<String> brokers = new ArrayList<>();
        brokers.add("192.168.199.144");
        brokers.add("192.168.199.176");
        brokers.add("192.168.199.101");
        //端口号
        int port = 9092;

        //主题topic
        String topic = "meng";

        int partition = 0;
        Long offset = 2L;
    }

    private String findLeader(List<String> brokers,int port,String topic,int partition){
//        new SimpleCons
        return null;
    }

    private void getData(){

    }
}
