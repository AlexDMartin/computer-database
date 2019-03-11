package com.excilys.validation;

import com.excilys.exception.validation.company.InvalidIdCompanyValidationException;
import com.excilys.exception.validation.computer.InvalidDiscontinuationDateComputerValidationException;
import com.excilys.exception.validation.computer.InvalidIdComputerValidationException;
import com.excilys.exception.validation.computer.InvalidIntroductionDateComputerValidationException;
import com.excilys.exception.validation.computer.InvalidNameComputerValidationException;
import com.excilys.exception.validation.computer.InvalidPrecedenceComputerValidationException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate Computer fields.
 */
public class ComputerValidation {

  /** Singleton implementation of ComputerValidation. */
  private static ComputerValidation validatorInstance = null;

  /**
   * Validates the Computer's id.
   * 
   * @param id The id of a Computer
   * @throws InvalidIdComputerValidationException This exception is thrown when the Computer's id is
   *         not valid
   */
  public void validateId(int id) throws InvalidIdComputerValidationException {

    Pattern p = Pattern.compile("^-?\\d+$");
    Matcher m = p.matcher(Integer.toString(id));

    if (!m.matches()) {
      throw new InvalidIdComputerValidationException("The computer\'s id is not valid.");
    }
  }

  /**
   * Validates the Computer's name.
   * 
   * @param name The name of a Computer
   * @throws InvalidNameComputerValidationException This exception is thrown when the Computer's
   *         name is not valid
   */
  public void validateName(String name) throws InvalidNameComputerValidationException {

    if (name == null || name.isEmpty()) {
      throw new InvalidNameComputerValidationException("The computer\'s name is not valid.");
    }

  }

  /**
   * Validates the Computer's introduction date.
   * 
   * @param introduction The introduction date of a Computer
   * @throws InvalidIntroductionDateComputerValidationException This exception is thrown when the
   *         Computer's introduction date is not valid
   */
  public void validateIntroductionDate(String introduction)
      throws InvalidIntroductionDateComputerValidationException {

    if (introduction == null || introduction.isEmpty()) {
      return;
    }

    Pattern p = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    Matcher m = p.matcher(introduction);

    if (!m.matches()) {
      throw new InvalidIntroductionDateComputerValidationException(
          "The computer\'s introduction date is not valid.");
    }

  }

  /**
   * Validates the Computer's discontinuation date.
   * 
   * @param discontinued The discontinuation date of a Computer
   * @throws InvalidIntroductionDateComputerValidationException This exception is thrown when the
   *         Computer's discontinuation date is not valid
   */
  public void validateDiscontinuationDate(String discontinued)
      throws InvalidDiscontinuationDateComputerValidationException {

    if (discontinued == null || discontinued.isEmpty()) {
      return;
    }

    Pattern p = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    Matcher m = p.matcher(discontinued);

    if (!m.matches()) {
      throw new InvalidDiscontinuationDateComputerValidationException(
          "The computer\'s discontinuation date is not valid.");
    }

  }

  /**
   * Checks that the introduction date is before the discontinuation date.
   * 
   * @param introduced The introduction date
   * @param discontinued The discontinuation date
   * @throws InvalidPrecedenceComputerValidationException Throwed when the discontinuation date is
   *         before the introduction date
   */
  public void validatePrecedence(Date introduced, Date discontinued)
      throws InvalidPrecedenceComputerValidationException {

    if (discontinued == null || introduced == null) {
      return;
    }

    if (discontinued.before(introduced)) {
      throw new InvalidPrecedenceComputerValidationException(
          "The discontinuation date is before the introduction date.");
    }

  }

  /** Singleton implementation of ComputerValidation. */
  private ComputerValidation() {}

  /**
   * Singleton implementation of ComputerValidation.
   *
   * @return single instance of ComputerValidation
   */
  public static ComputerValidation getInstance() {
    if (validatorInstance == null) {
      validatorInstance = new ComputerValidation();
    }
    return validatorInstance;
  }
}
