package com.excilys.controller;

import com.excilys.config.SpringConfig;
import com.excilys.view.CliMainView;
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
      applicationContext = Optional.of(new AnnotationConfigApplicationContext(SpringConfig.class));
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
