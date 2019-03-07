package com.excilys.exception.validation.computer;

public class InvalidNameComputerValidationException extends ComputerValidationException{

  /** SerialVersionUid. */
  private static final long serialVersionUID = -430428740827345304L;

  public InvalidNameComputerValidationException(String message) {
    super(message);
  }
}
