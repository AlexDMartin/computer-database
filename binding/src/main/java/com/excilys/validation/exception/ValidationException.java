package com.excilys.validation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationException extends Exception {

  /** SerialVersionUid. */
  private static final long serialVersionUID = 3148307574907699796L;

  private static final Logger logger = LoggerFactory.getLogger(ValidationException.class);
  
  public ValidationException(String message) {
    super(message);
    logger.error(message);
  }
}
