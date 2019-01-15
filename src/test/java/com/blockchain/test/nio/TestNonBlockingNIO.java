package com.blockchain.test.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlockingNIO {

    //客户端
    @Test
    public void client() throws Exception {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
        //2.切换成非阻塞模式
        sChannel.configureBlocking(false);
        //3. 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            String str = scanner.nextLine();
        String str = "ml";
        buffer.put((new Date().toString() + "\n" + str).getBytes());
        buffer.flip();
        sChannel.write(buffer);
        buffer.clear();
//        }
        sChannel.close();
    }

    //服务端
    @Test
    public void server() throws Exception {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //切换成非阻塞模式
        ssChannel.configureBlocking(false);
        //3.绑定连接
        ssChannel.bind(new InetSocketAddress(9090));
        //4.获取选择器
        Selector selector = Selector.open();
        //5.将通道注册到selector，并且制定"监听接收事件"
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询式的获取选择器上已经"准备就绪"的事件
        while (selector.select() > 0) {
            //7.获取当前选择器中所有注册的"选择键(已就绪的监听事件)"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8.获取"准备就绪"的事件
                SelectionKey sk = it.next();
                //9.判断是否是accept事件准备就绪
                if (sk.isAcceptable()) {
//                    System.out.println(sk.toString()+"isAcceptable----"+System.currentTimeMillis());
                    //10.若接收状态就绪，就获取客户端的连接
                    SocketChannel sChannel = ssChannel.accept();
                    //11.切换非阻塞模式
                    sChannel.configureBlocking(false);
                    //12.将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
//                    System.out.println(sk.toString()+"isReadable----"+System.currentTimeMillis());
                    //12.获取当前选择器上"读就绪"状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //13.读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = sChannel.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
                //15.取消选择键 SelectionKey
                it.remove();
            }
        }
    }
}
