package com.blockchain.test.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞式
 */
public class TestBlockingNIO2 {

    //客户端
    @Test
    public void client() throws Exception {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
        FileChannel fChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (fChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.shutdownOutput();

        //接收反馈
        int len;
        while ((len = sChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }
        fChannel.close();
        sChannel.close();
    }

    //服务端
    @Test
    public void server() throws Exception {
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        FileChannel fChannel = FileChannel.open(Paths.get("11.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //2.绑定连接
        ssChannel.bind(new InetSocketAddress(9090));
        //3.获取客户端连接通道
        SocketChannel sChannel = ssChannel.accept();
        //4.接收客户端的数据，并保存到本地
        while (sChannel.read(buffer) != -1) {
            buffer.flip();
            fChannel.write(buffer);
            buffer.clear();
        }
        //发送反馈给客户端
        buffer.put("接收成功".getBytes());
        buffer.flip();
        sChannel.write(buffer);
//        buffer.clear();
        sChannel.shutdownOutput();

        fChannel.close();
        sChannel.close();
        ssChannel.close();
    }
}
