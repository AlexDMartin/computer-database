package com.excilys.api.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiCompanyException extends Exception {

  private static final long serialVersionUID = -5221917382238167687L;

  private static final Logger logger = LoggerFactory.getLogger(ApiCompanyException.class);

  public ApiCompanyException(String message) {
    super(message);
    logger.error(message);
  }
}
