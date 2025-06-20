package com.dollee.bank.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
    "com.dollee.bank.account.infra.entity",
    "com.dollee.bank.policy.infra.entity"
})
@EnableJpaRepositories(basePackages = {
    "com.dollee.bank.account.infra.repository",
    "com.dollee.bank.policy.infra.repository"
})
public class JpaConfig {

}
