package api.restservice;

import domain.BatchController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

	private static BatchController batchCon = new BatchController();

	public static void main(String[] args) {

		SpringApplication.run(RestServiceApplication.class, args);
	}

}
