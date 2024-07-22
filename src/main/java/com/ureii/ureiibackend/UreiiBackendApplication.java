package com.ureii.ureiibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ureii.ureiibackend.repository")
public class UreiiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UreiiBackendApplication.class, args);
    }

}
