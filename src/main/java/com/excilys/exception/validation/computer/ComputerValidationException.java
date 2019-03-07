package com.excilys.exception.validation.computer;

import com.excilys.exception.validation.ValidationException;

public class ComputerValidationException extends ValidationException{

  /** SerialVersionUid. */
  private static final long serialVersionUID = -1383597915176143863L;
  
  public ComputerValidationException(String message) {
    super(message);
  }

}
