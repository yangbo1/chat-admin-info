package com.yb.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.yb.chat.client"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ChatAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAdminServiceApplication.class, args);
	}
}
