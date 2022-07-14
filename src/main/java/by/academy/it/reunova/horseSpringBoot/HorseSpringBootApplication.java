package by.academy.it.reunova.horseSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HorseSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorseSpringBootApplication.class, args);
	}

}
