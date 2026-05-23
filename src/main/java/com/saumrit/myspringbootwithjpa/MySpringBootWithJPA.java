package com.saumrit.myspringbootwithjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(escapeCharacter = '*')
@EntityScan(basePackages = "com.saumrit.myspringbootwithjpa.model")
//@EnableTransactionManagement
public class MySpringBootWithJPA {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootWithJPA.class, args);
	}

}
