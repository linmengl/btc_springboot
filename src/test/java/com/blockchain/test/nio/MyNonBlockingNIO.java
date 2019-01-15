package com.blockchain.test.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class MyNonBlockingNIO {


    //客户端
    @Test
    public void client() throws Exception{
        //1.建立连接
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9098));
        sChannel.configureBlocking(false);
        FileChannel fChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        //2.缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3.将数据从fChannel读到缓冲区
        while (fChannel.read(buffer)!=-1){
            buffer.flip();
            //4.将buffer中的数据写到sChannel
            sChannel.write(buffer);
            buffer.clear();
        }
        fChannel.close();
        sChannel.close();
    }
    //服务端
    @Test
    public void server() throws Exception{
        //1.建立连接
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2.绑定
        ssChannel.bind(new InetSocketAddress(9098));
        //3.开启非阻塞
        ssChannel.configureBlocking(false);
        //4.将channel注册到选择器
        Selector selector = Selector.open();
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0){
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey sk = it.next();
                if (sk.isAcceptable()){
                    //若状态允许，获取连接
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector,SelectionKey.OP_READ);
                }else if (sk.isReadable()){
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    FileChannel fChannel = FileChannel.open(Paths.get("22.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (sChannel.read(buffer)!=-1){
                        buffer.flip();
                        fChannel.write(buffer);
                        buffer.clear();
                    }
                }
                it.remove();
            }
        }
    }
}
