package anovaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
    public static Object st;

	public static void main(String[] args) throws Exception {        
        SpringApplication.run(Application.class, args);
    }

}