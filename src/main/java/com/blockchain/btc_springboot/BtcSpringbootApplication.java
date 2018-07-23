package com.blockchain.btc_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BtcSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtcSpringbootApplication.class, args);
	}
}
