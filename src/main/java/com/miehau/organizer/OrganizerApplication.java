package com.miehau.organizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class OrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizerApplication.class, args);
    }

}
