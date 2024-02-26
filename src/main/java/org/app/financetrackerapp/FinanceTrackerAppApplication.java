package org.app.financetrackerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.app")
public class FinanceTrackerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceTrackerAppApplication.class, args);
    }

}
