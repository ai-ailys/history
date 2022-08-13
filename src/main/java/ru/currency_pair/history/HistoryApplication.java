package ru.currency_pair.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class HistoryApplication {

    public static void main(String[] args) {

        SpringApplication.run(HistoryApplication.class, args);

       // System.out.println(LocalDateTime.now());

    }


}
