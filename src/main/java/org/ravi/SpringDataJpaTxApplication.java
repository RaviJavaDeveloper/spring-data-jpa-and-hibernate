package org.ravi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringDataJpaTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaTxApplication.class, args);
	}

}
