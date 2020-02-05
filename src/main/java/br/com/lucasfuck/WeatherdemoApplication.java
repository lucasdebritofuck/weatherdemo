package br.com.lucasfuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WeatherdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherdemoApplication.class, args);
	}

}
