package com.bookstore.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 
 * Main class to start BookStoreApplication.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@SpringBootApplication
@EnableRetry
@ComponentScan(basePackages = "com.bookstore")
public class BookStoreApplication {

    /**
     * 
     * Main method to start application.
     * 
     * @param args
     *            - Array string values could be passed as arguments when
     *            application is started.
     */
    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }
}
