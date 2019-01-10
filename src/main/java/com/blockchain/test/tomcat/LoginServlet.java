package com.blockchain.test.tomcat;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(pwd)
                && userName.equals("admin") && pwd.equals("123")){
            response.writerFile("html/login.html");
        }
    }
}
