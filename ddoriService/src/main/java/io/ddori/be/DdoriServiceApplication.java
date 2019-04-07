package io.ddori.be;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@MapperScan(basePackages = "io.ddori.be")
public class DdoriServiceApplication {

	public static void main(String[] args) {
		// context path 변경 처리
		// System.setProperty("server.servlet.context-path", "/baeldung");
		SpringApplication.run(DdoriServiceApplication.class, args);
	}

}
