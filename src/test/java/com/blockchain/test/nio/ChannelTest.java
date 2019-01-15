package com.blockchain.test.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelTest {

    @Test
    public void test5() throws Exception {
        Charset cs1 = Charset.forName("GBK");
        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("孟林");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = cs1.encode(cBuf);

        //解码
        CharBuffer cBuf2 = cs1.decode(bBuf);
        System.out.println(cBuf2.toString());

        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        //乱码
        System.out.println(cBuf3);
    }

    @Test
    public void test4() throws Exception {
        RandomAccessFile file = new RandomAccessFile("1.txt", "rw");
        FileChannel inChannel = file.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        ByteBuffer buffer2 = ByteBuffer.allocate(10240);

        ByteBuffer[] bufs = {buffer1, buffer2};
        inChannel.read(bufs);

        for (ByteBuffer buffer : bufs) {
            buffer.flip();
        }

        RandomAccessFile outFile = new RandomAccessFile("4.txt", "rw");
        FileChannel outChannel = outFile.getChannel();
        outChannel.write(bufs);

        outChannel.close();
        inChannel.close();
        outFile.close();
        file.close();
    }

    //通道之间的数据传输(直接缓冲区)
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.pdf"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.pdf"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    //2.使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("1.pdf"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.pdf"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMapBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMapBuf.limit()];
        inMapBuf.get(dst);
        outMapBuf.put(dst);
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //1.利用通道完成文件的复制（非直接缓冲区）
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inputStream = new FileInputStream("1.pdf");
            outputStream = new FileOutputStream("2.pdf");

            inChannel = inputStream.getChannel();
            outChannel = outputStream.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (inChannel.read(buf) != -1) {
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
