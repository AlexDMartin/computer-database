package com.excilys.console.controller;

import com.excilys.console.config.ConsoleSpringConfig;
import com.excilys.console.view.CliMainView;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CliRenderer {

  private static final Logger logger = LoggerFactory.getLogger(CliRenderer.class);

  /**
   * Entrypoint for the CLI Application.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    Optional<ApplicationContext> applicationContext = Optional.empty();
    
    try {
      applicationContext = Optional.of(new AnnotationConfigApplicationContext(ConsoleSpringConfig.class));
      CliMainView cliMainView = applicationContext.get().getBean(CliMainView.class);
      cliMainView.render();
    } catch (BeansException beansException) {
      logger.warn(beansException.getMessage());
    } finally {
      if (applicationContext.isPresent()) {
        ((ConfigurableApplicationContext) applicationContext.get()).close();
      }
    }
    
  }
}
