package com.blockchain.test.tomcat;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigUtils {

    public static Map<String,String> getClassName(String path) throws Exception{
        Map<String,String> handleMap = new HashMap<>();
        SAXReader reader = new SAXReader();
        File file = new File(path);
        Document document = reader.read(file);

        Element rootElement = document.getRootElement();
        List<Element> childElements = rootElement.elements();
        for (Element childElement:childElements){
            String key = childElement.element("url.pattern").getText();
            String value = childElement.element("servlet.name").getText();
            handleMap.put(key,value);
        }
        return handleMap;
    }
}
