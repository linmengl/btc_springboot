package com.blockchain.test.tomcat;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Data
public class HttpRequest {
    private String uri;

    private Map<String,String> paramMap = new HashMap<>(32);
    //获取参数
    public String getParameter(String key){
        return paramMap.get(key);
    }

    public HttpRequest(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        int read = inputStream.read(buff);
        if (read > 0) {
            String msg = new String(buff,0,read);
//            uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1") - 1);
            int startIndex = msg.indexOf("GET")+4;
            if (msg.contains("POST")){
                startIndex = msg.indexOf("POST")+5;
            }
            int endIndex = msg.indexOf("HTTP/1.1") - 1;
            uri = msg.substring(startIndex,endIndex);
            System.out.println("uri:"+uri+"--------------");
            String paramString = null;
            if (msg.startsWith("GET")){
                System.out.println("getUri:"+uri+"--------------");
            }else if (msg.startsWith("POST")){
                int paramStart = msg.lastIndexOf("\n");
                paramString = msg.substring(paramStart+1);//加上1就到下一行了
                if (StringUtils.isNotBlank(paramString)){
                    if (paramString.contains("&")){
                        String[] params = paramString.split("&");
                        for (String param:params){
                            String[] parmTemp = param.split("=");
                            paramMap.put(parmTemp[0],parmTemp[1]);
                        }
                    }else if (paramString.contains("=")){
                        String[] parmTemp = paramString.split("=");
                        paramMap.put(parmTemp[0],parmTemp[1]);
                    }
                }
            }
        } else {
            System.out.println("bad request!");
        }
    }
}
