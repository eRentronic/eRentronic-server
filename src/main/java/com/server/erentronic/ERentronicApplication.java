package com.server.erentronic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableWebMvc
public class ERentronicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ERentronicApplication.class, args);
	}

}
