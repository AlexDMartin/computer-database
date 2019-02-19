package com.excilys.controller;

import java.util.Date;
import java.util.Optional;

import com.excilys.dao.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class Validator.
 */
public class Validator {

  /** The validator instance. */
  private static Validator validatorInstance = null;

  /**
   * Instantiates a new validator.
   */
  private Validator() {
  }

  /**
   * Gets the single instance of Validator.
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
   * Precedence.
   *
   * @param introduced the introduced
   * @param discontinued the discontinued
   * @return true, if successful
   */
  public boolean precedence(Date introduced, Date discontinued) {
    return introduced.before(discontinued);
  }

  /**
   * Checks if is valid.
   *
   * @param date the date
   * @return true, if is valid
   */
  public boolean isValid(String date) {
    return true;
  }

  /**
   * Company exists.
   *
   * @param company the company
   * @return true, if successful
   */
  public boolean companyExists(Optional<Company> company) {
    return company.isPresent();
  }

  /**
   * Checks if is yes.
   *
   * @param input the input
   * @return true, if is yes
   */
  public boolean isYes(String input) {
    return input.charAt(0) == 'y';
  }
}
