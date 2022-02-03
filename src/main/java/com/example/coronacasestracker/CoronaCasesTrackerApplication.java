package com.example.coronacasestracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaCasesTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronaCasesTrackerApplication.class, args);
    }

}
