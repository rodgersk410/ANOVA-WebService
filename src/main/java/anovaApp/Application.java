package anovaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This class contains the main method where the program starts
 */
@SpringBootApplication
public class Application {
	
    public static Object st;

	public static void main(String[] args) throws Exception {        
        SpringApplication.run(Application.class, args);
    }

}