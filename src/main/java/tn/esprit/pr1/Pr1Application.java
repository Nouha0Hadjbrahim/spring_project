package tn.esprit.pr1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Pr1Application {

    public static void main(String[] args) {
        SpringApplication.run(Pr1Application.class, args);
    }

}
