package com.blockchain.test.tomcat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private OutputStream os = null;
    public HttpResponse(OutputStream os){
        this.os = os;
    }

    //E:\project\github\btc_springboot\src\main\resources\templates\login.html
    public void writerFile(String path) throws IOException{
        FileInputStream fs = new FileInputStream(path);
        byte[] buff = new byte[1024];
        int len = 0;
        os.write("http/1.1 200 OK\n \n".getBytes());
        while ((fs.read(buff))!=-1){
            os.write(buff,0,len);
        }
        fs.close();
        os.flush();
        os.close();
    }
}
