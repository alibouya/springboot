package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableAutoConfiguration
//(exclude={UserDetailsServiceAutoConfiguration.class})
@ComponentScan({"controller", "services","configure"})
@EntityScan("com.my.sso.server.models")


public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
