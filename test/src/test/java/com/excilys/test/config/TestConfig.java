package com.excilys.test.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@ComponentScan(basePackages = {"com.excilys.dao", "com.excilys.dao.mappers",
    "com.excilys.controller", "com.excilys.dto", "com.excilys.validation",
    "com.excilys.exception.validation.company", "com.excilys.exception.validation.computer",
    "com.excilys.persistance.utils", "com.excilys.service", "com.excilys.validation",
    "com.excilys.view", "com.excilys.web.controller", "com.excilys.model"})
@PropertySource(value = {"classpath:hikari.properties"})
public class TestConfig {

  @Autowired
  private Environment environment;
  
  /**
   * Get Data Source.
   * @return DataSource
   */
  @Bean
  public DataSource getDataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(environment.getRequiredProperty("jdbcUrl"));
    dataSource.setUsername(environment.getRequiredProperty("dataSource.user"));
    dataSource.setPassword(environment.getRequiredProperty("dataSource.password"));
    dataSource.setDriverClassName(environment.getRequiredProperty("driverClassName"));
    return dataSource;
  }

  /**
   * Session Factory.
   * 
   * @return SessionFactory
   */
  @Bean
  public SessionFactory sessionFactory() {

    LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(getDataSource());
    builder.scanPackages("com.excilys.dao.model");
    builder.addProperties(hibernateProperties());

    return builder.buildSessionFactory();
  }

  /**
   * Transaction Manager.
   * @return TransactionManager
   */
  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory());
    return transactionManager;
  }
  
  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
    
    return hibernateProperties;
  }

}
