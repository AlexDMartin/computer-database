package com.excilys.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Locale;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan(basePackages = {"com.excilys.dao", "com.excilys.dao.mappers",
    "com.excilys.controller", "com.excilys.dto", "com.excilys.validation",
    "com.excilys.exception.validation.company", "com.excilys.exception.validation.computer",
    "com.excilys.persistance.utils", "com.excilys.service", "com.excilys.validation",
    "com.excilys.view", "com.excilys.web.controller", "com.excilys.model"})
@PropertySource(value = {"classpath:hikari.properties"})
@EnableWebMvc
public class SpringConfig implements WebApplicationInitializer, WebMvcConfigurer {

  @Autowired
  private Environment environment;

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(SpringConfig.class);
    
    servletContext.addListener(new ContextLoaderListener(rootContext));
  }
  
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeInterceptor());
  }

  /**
   * View Resolver.
   * 
   * @return bean
   */
  @Bean
  public ViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/views/");
    bean.setSuffix(".jsp");

    return bean;
  }

  /**
   * Reloadable Resource Bundle Message Source.
   * @return ReloadableResourceBundleMessageSource
   */
  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  /**
   * Locale Resolver.
   * @return CookieLocaleResolver
   */
  @Bean
  public CookieLocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setDefaultLocale(Locale.FRENCH);
    localeResolver.setCookieName("my-locale-cookie");
    localeResolver.setCookieMaxAge(3600);
    return localeResolver;
  }

  /**
   * Locale Change Interceptor.
   * @return LocaleChangeInterceptor 
   */
  @Bean
  public LocaleChangeInterceptor localeInterceptor() {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("lang");
    return interceptor;
  }
  
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
