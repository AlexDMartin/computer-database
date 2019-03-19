package com.excilys.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
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
    "com.excilys.view", "com.excilys.viewcontroller"})
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
}
