package com.blockchain.test;

import org.apache.tools.ant.taskdefs.Sleep;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ForTest {

    public static void main(String[] args) {
        //String msg = "hello";
        ////1.分配一个指定大小的缓冲区
        //ByteBuffer buf = ByteBuffer.allocate(1024);
		//
        ////2.利用put()存入数据到缓冲区中
        //buf.put(msg.getBytes());
		//
        ////3.切换读取数据模式
        //buf.flip();
		//
        ////4.利用get()获取缓冲区中的数据
        //byte[] dst = new byte[buf.limit()];
        //buf.get(dst);
		//
        ////5.rewind()，可重复读数据
        //buf.rewind();
		//
        ////6. clear()：清空缓冲区，但是缓冲区中的数据依然存在，但是处于"被遗忘"状态
        //buf.clear();



        //while (true){
            System.out.println("aa");
        //}
        try {
            Thread.sleep(50000);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
