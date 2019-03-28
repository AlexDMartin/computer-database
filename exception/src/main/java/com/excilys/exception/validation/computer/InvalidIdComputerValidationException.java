package com.excilys.exception.validation.computer;

public class InvalidIdComputerValidationException extends ComputerValidationException {

  /** SerialVersionUid. */
  private static final long serialVersionUID = -4275354094793608309L;

  public InvalidIdComputerValidationException(String message) {
    super(message);
  }
}
