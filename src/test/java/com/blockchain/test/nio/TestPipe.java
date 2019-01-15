package com.blockchain.test.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class TestPipe {

    @Test
    public void test() throws Exception{
        //1.获取管道
        Pipe pipe = Pipe.open();
        //2.缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3.sink通道
        Pipe.SinkChannel sink = pipe.sink();
        String str = "好啊";
        buffer.put(str.getBytes());
        buffer.flip();
        //4.将缓冲区的数据写入管道
        sink.write(buffer);
        buffer.clear();
        //5.获取source通道
        Pipe.SourceChannel source = pipe.source();
        //6.将source通道的数据读到buffer中
        source.read(buffer);
        System.out.println(new String(buffer.array()));

        source.close();
        sink.close();
    }
}
