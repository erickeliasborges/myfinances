package br.edu.utfpr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ApiJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiJavaApplication.class, args);
	}

}
