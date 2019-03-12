package com.excilys.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "com.excilys.dao",
    "com.excilys.dao.mappers",
    "com.excilys.controller",
    "com.excilys.dto",
    "com.excilys.validation",
    "com.excilys.exception.validation.company",
    "com.excilys.exception.validation.computer",
    "com.excilys.persistance.utils",
    "com.excilys.service",
    "com.excilys.validation",
    "com.excilys.view"
    })
public class SpringConfig {
  
}
