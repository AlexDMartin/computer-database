package com.excilys.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseCallException extends Exception {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseCallException.class);
  private static final long serialVersionUID = 189115035888137682L;

  public DatabaseCallException(String message) {
    super(message);
    logger.error(message);
  }

}
