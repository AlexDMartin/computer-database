package com.excilys.validation;

import com.excilys.dao.model.Computer;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.exception.validation.computer.InvalidDiscontinuationDateComputerValidationException;
import com.excilys.exception.validation.computer.InvalidIdComputerValidationException;
import com.excilys.exception.validation.computer.InvalidIntroductionDateComputerValidationException;
import com.excilys.exception.validation.computer.InvalidNameComputerValidationException;
import com.excilys.exception.validation.computer.InvalidPrecedenceComputerValidationException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class ComputerValidation {

  private ComputerValidation() {}

  /**
   * Validates a computer.
   * 
   * @param computer the computer that will be checked
   * @throws ComputerValidationException the errors that will be throwed
   */
  public void validate(Computer computer) throws ComputerValidationException {
    this.validateId(computer.getId());
    this.validateName(computer.getName());
    this.validateIntroductionDate(computer.getIntroduced().toString());
    this.validateDiscontinuationDate(computer.getDiscontinued().toString());
    this.validatePrecedence(computer.getIntroduced(), computer.getDiscontinued());
  }
  
  /**
   * Validates the Computer's id.
   * 
   * @param id The id of a Computer
   * @throws InvalidIdComputerValidationException This exception is thrown when the Computer's id is
   *         not valid
   */
  private void validateId(int id) throws InvalidIdComputerValidationException {

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
  private void validateName(String name) throws InvalidNameComputerValidationException {

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
  private void validateIntroductionDate(String introduction)
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
  private void validateDiscontinuationDate(String discontinued)
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
  private void validatePrecedence(Date introduced, Date discontinued)
      throws InvalidPrecedenceComputerValidationException {

    if (discontinued == null || introduced == null) {
      return;
    }

    if (discontinued.before(introduced)) {
      throw new InvalidPrecedenceComputerValidationException(
          "The discontinuation date is before the introduction date.");
    }

  }
}
