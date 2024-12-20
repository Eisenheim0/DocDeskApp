package uoc.edu.docdeskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DocDeskAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocDeskAppApplication.class, args);
	}

}
