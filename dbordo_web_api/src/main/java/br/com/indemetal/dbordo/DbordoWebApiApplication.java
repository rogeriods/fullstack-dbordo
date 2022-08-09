package br.com.indemetal.dbordo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class DbordoWebApiApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		System.out.println("App running in UTC timezone: " + new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(DbordoWebApiApplication.class, args);
	}

}
