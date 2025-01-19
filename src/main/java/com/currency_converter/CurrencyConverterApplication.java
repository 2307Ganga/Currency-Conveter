package com.currency_converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencyConverterApplication {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterApplication.class);

    public static void main(String[] args) {
        logger.info("Starting CurrencyConverterApplication...");
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            logger.info("Currency Converter Application started successfully!");
        };
    }
}
