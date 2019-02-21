package com.blockchain.test;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {

    @Test
    public void client() throws Exception {
        Socket socket = new Socket("127.0.0.1", 9990);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("好的".getBytes());
        outputStream.close();
        socket.close();
    }

    @Test
    public void server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9990);
        Socket accept = null;
        while (true) {
            accept = serverSocket.accept();
            System.out.println("accept............");
            InputStream inputStream = accept.getInputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                System.out.println("while................");
                System.out.println(new String(buf, 0, len));
            }
            accept.close();
        }
//        serverSocket.close();
    }



}
