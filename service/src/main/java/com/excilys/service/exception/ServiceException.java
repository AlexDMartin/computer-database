package com.excilys.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Exception {

  public static final Logger logger = LoggerFactory.getLogger(ServiceException.class);
  private static final long serialVersionUID = 1241406497412956059L;

  public ServiceException(String message) {
    super(message);
    logger.error(message);
  }

}
