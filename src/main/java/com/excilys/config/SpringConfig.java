package com.excilys.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(
    basePackages = {"com.excilys.dao", "com.excilys.dao.mappers", "com.excilys.controller",
        "com.excilys.dto", "com.excilys.validation", "com.excilys.exception.validation.company",
        "com.excilys.exception.validation.computer", "com.excilys.persistance.utils",
        "com.excilys.service", "com.excilys.validation", "com.excilys.view"})
@PropertySource(value = { "classpath:hikari.properties" })
public class SpringConfig implements WebApplicationInitializer {

  @Autowired
  private Environment environment;
  
  /**
   * Data source.
   *
   * @return the hikari data source
   */
  @Bean
  public HikariDataSource dataSource() {

    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl(environment.getRequiredProperty("jdbcUrl"));
    ds.setUsername(environment.getRequiredProperty("dataSource.user"));
    ds.setPassword(environment.getRequiredProperty("dataSource.password"));
    ds.setDriverClassName(environment.getRequiredProperty("driverClassName"));
    return ds;
  }
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(SpringConfig.class);

    servletContext.addListener(new ContextLoaderListener(rootContext));
  }

}
