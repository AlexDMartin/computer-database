package com.excilys.exception.api.computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiComputerException extends Exception {

  private static final long serialVersionUID = 1956371846854881494L;

  private static final Logger logger = LoggerFactory.getLogger(ApiComputerException.class);

  public ApiComputerException(String message) {
    super(message);
    logger.error(message);
  }
}
