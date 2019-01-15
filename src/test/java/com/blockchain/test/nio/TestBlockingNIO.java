package com.blockchain.test.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIO {

    //客户端
    @Test
    public void client() throws Exception {
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
        FileChannel fChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        //2.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3.读取本地文件，并发送到服务端
        while (fChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        //4.关闭通道
        fChannel.close();
        sChannel.close();
    }

    //服务端
    @Test
    public void server() throws Exception{
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        FileChannel fChannel = FileChannel.open(Paths.get("11.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //2.绑定连接
        ssChannel.bind(new InetSocketAddress(9090));
        //3.获取客户端连接通道
        SocketChannel sChannel = ssChannel.accept();
        //4.接收客户端的数据，并保存到本地
        while (sChannel.read(buffer) != -1){
            buffer.flip();
            fChannel.write(buffer);
            buffer.clear();
        }
        fChannel.close();
        sChannel.close();
        ssChannel.close();
    }
}
