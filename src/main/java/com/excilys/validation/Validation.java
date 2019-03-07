package com.excilys.validation;

import com.excilys.dao.model.Company;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.ValidationException;

/**
 * Implements all methods to validate user inputs in the application. All methods return true if the
 * string passed to the method is valid, or throw a ValidationException otherwise.
 */
public class Validation {

  /** Singleton implementation of Validator. */
  private static Validation validatorInstance = null;

  /** Singleton implementation of Validator. */
  private Validation() {}

  /**
   * Singleton implementation of Validator.
   *
   * @return single instance of Validator
   */
  public static Validation getInstance() {
    if (validatorInstance == null) {
      validatorInstance = new Validation();
    }
    return validatorInstance;
  }

  /**
   * Validate ids.
   * 
   * @param id Any id
   * @return true if the id valid
   * @throws ValidationException if the id is invalid
   */
  public boolean validateId(String id) throws ValidationException {
    if (id == null) {
      return true;
    }
    Pattern p = Pattern.compile("^-?\\d+$");
    Matcher m = p.matcher(id);
    if (!m.matches()) {
      throw new ValidationException("The id entered is not valid");
    }
    return true;
  }

  /**
   * Validate names.
   * 
   * @param name Any name
   * @return true if the name is valid
   * @throws ValidationException if the name is invalid
   */
  public boolean validateName(String name) throws ValidationException {
    if (name == null || name.isEmpty()) {
      throw new ValidationException("Computer's name shouldn\'t be null nor empty.");
    }
    return true;
  }

  /**
   * Validate date format to match "yyyy-MM-dd".
   * 
   * @param date Any date
   * @return true if the date format matches date
   * @throws ValidationException Validation exception
   */
  public boolean validateDate(String date) throws ValidationException {
    if (date == null) {
      return true;
    }
    Pattern p = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    Matcher m = p.matcher(date);
    if (!m.matches()) {
      throw new ValidationException("The date entered is not valid (\"yyyy-MM-dd\")");
    }
    return true;
  }

  /**
   * Validate the fact that the introduced is before the discontinuation date.
   * 
   * @param introduced The introduction date of the computer
   * @param discontinued The discontinuation date of the computer
   * @return true if the introduction date is before the discontinuation date
   * @throws ValidationException Validation exception
   */
  public boolean validatePrecedence(Date introduced, Date discontinued) throws ValidationException {
    if (introduced == null || discontinued == null) {
      return true;
    }
    if (introduced.after(discontinued)) {
      throw new ValidationException(
          "Introduction date is more recent than the discontinuation date.");
    }
    return true;
  }

  /**
   * Validate that the company exists when it's called by the DAOs.
   * 
   * @param company the company
   * @return true
   */
  public boolean validateCompany(Company company) throws ValidationException {
    if (company == null) {      
      return true;
    }
    if (validateId(Integer.toString(company.getId())) && validateName(company.getName())) {
      return true;
    }
    throw new ValidationException("Company is not valid");
  }
}
