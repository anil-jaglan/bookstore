package com.bookstore.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages =
    { GeneralConstants.APPLICATION_BASE_PACKAGE })
@EnableJpaRepositories(basePackages =
    { GeneralConstants.APPLICATION_BASE_PACKAGE })
@EnableTransactionManagement
public class DataStoreConfig {

}
