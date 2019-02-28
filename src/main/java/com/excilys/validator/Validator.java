package com.excilys.validator;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

import com.excilys.dao.model.Company;

/**
 * Implements all methods to validate user inputs in the application.
 * All methods return true if the string passed to the method is valid, or throw a ValidationException otherwise.
 */
public class Validator {

  /** Singleton implementation of Validator. */
  private static Validator validatorInstance = null;

  /** Singleton implementation of Validator. */
  private Validator() {}

  /**
   * Singleton implementation of Validator.
   *
   * @return single instance of Validator
   */
  public static Validator getInstance() {
    if (validatorInstance == null) {
      validatorInstance = new Validator();
    }
    return validatorInstance;
  }
  
  /**
   * Validate ids.
   * @param String id
   * @return true if the id valid 
   * @throws ValidationException if the id is invalid
   */
  public boolean validateId(String id) throws ValidationException {
    if (id == null) {
      throw new ValidationException("The id is null");
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
   * @param String name
   * @return true if the name is valid
   * @throws ValidationException if the name is invalid
   */
  public boolean validateName(String name) throws ValidationException {
    if (name == null||name.isEmpty()) {
      throw new ValidationException("Computer's name shouldn\'t be null nor empty.");
    }
    return true;
  }
  
  /**
   * Validate date format to match "dd/MM/yyyy"
   * @param String date
   * @return true if the date format matches date
   * @throws ValidationException
   */
  public boolean validateDate(String date) throws ValidationException {
    if(date == null) return true;
    Pattern p = Pattern.compile("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$");
    Matcher m = p.matcher(date);
    if (!m.matches()) {
      throw new ValidationException("The date entered is not valid");
    }
    return true;
  }
  
  /**
   * Validate date format to match "yyyy-MM-dd"
   * @param String date
   * @return true if the date format matches date
   * @throws ValidationException
   */
  public boolean validateReversedDate(String date) throws ValidationException {
    if(date == null) return true;
    Pattern p = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    Matcher m = p.matcher(date);
    if (!m.matches()) {
      throw new ValidationException("The date entered is not valid");
    }
    return true;
  }
  
  /**
   * Validate the fact that the introduced is before the discontinuation date
   * @param introduced
   * @param discontinued
   * @return true if the introduction date is before the discontinuation date
   * @throws ValidationException
   */
  public boolean validatePrecedence(Date introduced, Date discontinued)
      throws ValidationException {
    if (introduced.after(discontinued)) {
      throw new ValidationException(
          "Introduction date is more recent than the discontinuation date.");
    }
    return true;
  }
  
  /**
   * Validate that the company exists when it's called by the DAOs
   * @param company the company
   * @return true 
   */
  public boolean validateCompany(Company company) throws ValidationException {
    if(company == null) return true;
    if(validateId(Integer.toString(company.getId()))&& validateName(company.getName())) {      
      return true;
    }
    throw new ValidationException("Company is not valid");
  }
}
