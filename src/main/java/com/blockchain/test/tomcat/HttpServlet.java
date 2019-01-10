package com.blockchain.test.tomcat;

public abstract class HttpServlet {
    public abstract void service(HttpRequest request,HttpResponse response) throws Exception;
}
