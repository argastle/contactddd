package de.dhbw.softwareengineering.contactddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "de.dhbw.softwareengineering.contactddd.plugins")
public class MainApplication {
    public static void main(String[] args) {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(MainApplication.class, args);
    }
}
