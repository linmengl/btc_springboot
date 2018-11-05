package com.blockchain.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.blockchain.test"})
public class BtcSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcSpringbootApplication.class, args);
	}
}
