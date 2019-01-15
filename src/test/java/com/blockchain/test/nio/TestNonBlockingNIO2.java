package com.blockchain.test.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlockingNIO2 {

    //客户端
    @Test
    public void client() throws Exception {
        //1. 获取通道
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String name = "haode";
        buffer.put((new Date().toString()+"\n"+name).getBytes());
        buffer.flip();
        dc.send(buffer,new InetSocketAddress("127.0.0.1",9099));
        buffer.clear();
        dc.close();
    }

    //服务端
    @Test
    public void server() throws Exception {
        //1. 获取通道
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(new InetSocketAddress(9099));
        Selector selector = Selector.open();
        dc.register(selector,SelectionKey.OP_READ);

        while (selector.select() > 0){
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            if (it.hasNext()){
                SelectionKey sk = it.next();
                if (sk.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    dc.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array()));
                    buffer.clear();
                }
            }
            it.remove();
        }
    }
}
