package com.blockchain.test.tomcat;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server {

    private static int count = 0;

    private static int port = 9000;

    private static Map<String,String> handleMap = new HashMap<>();



    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        //http底层就是socket
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器初始化完成，server.port:" + port);
            while (true) {
                //等待客户端连接
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                HttpRequest request = new HttpRequest(inputStream);
                String uri = request.getUri();
                if (StringUtils.isBlank(uri)){
                    System.out.println("uri为null");
                    continue;
                }
                OutputStream os = socket.getOutputStream();
                HttpResponse response = new HttpResponse(os);
                //需要判断是否是静态资源
                if (isStatic(uri)){
                    response.writerFile(uri.substring(1));
                }else if (uri.endsWith(".action")){
//                    LoginServlet servlet = new LoginServlet();
//                    servlet.service(request,response);
                    handleMap = ConfigUtils.getClassName("WEB-INF/web.xml");
                    for (Map.Entry<String,String> entry:handleMap.entrySet()){
                        if (uri.endsWith(entry.getKey())){
                            HttpServlet servlet = (HttpServlet) Class.forName(entry.getValue()).newInstance();
                            servlet.service(request,response);
                        }
                    }

                }
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] staticSuffixs = {"html","js","jpg","css","png","jpeg"};
    //是否是静态资源
    private static boolean isStatic(String uri) {
        for (String suffixs:staticSuffixs){
            if (uri.endsWith(".".concat(suffixs))){
                return true;
            }
        }
        return false;
    }
}
