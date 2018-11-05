package com.blockchain.test;

import com.blockchain.test.bean.JsonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan(value = "com.blockchain.test.controller")
public class BtcSpringbootApplicationTests {

	@Test
	public void contextLoads() {
		//ApplicationContext context = new AnnotationConfigApplicationContext(TestController.class);
		//TestController controller = (TestController)context.getBean("testController");
		//controller.fiber();
		//System.out.println("aaa");
		//BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
		ApplicationContext context = new ClassPathXmlApplicationContext("com/blockchain/btc_springboot/beans.xml");
		//JsonResult jsonResult = (JsonResult)factory.getBean("jsonResult");
		JsonResult jsonResult = (JsonResult)context.getBean("jsonResult");
		System.out.println(jsonResult.toString());
	}

}
