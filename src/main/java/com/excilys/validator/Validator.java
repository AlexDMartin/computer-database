package com.excilys.validator;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

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
   * Company exists.
   *
   * @param company the company
   * @return true, if successful
   */
  public boolean validateCompany(Optional<Company> company) throws ValidationException{
    if(!company.isPresent()) {
      throw new ValidationException("Company doesn\'t exist.");
    }
    return true;
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

  /**
   * 
   * @param name
   * @return
   */
  public boolean validateName(String name) throws ValidationException{
    if(name == null) {
      throw new ValidationException("Computer's name shouldn\'t be null.");
    }
    return true;
  }

  public boolean validatePrecedence(Date introducedDate, Date discontinuedDate) throws ValidationException {
    if(introducedDate.after(discontinuedDate)) {
      throw new ValidationException("Introduction date is more recent than the discontinuation date.");
    }
    return true;
  }

  public boolean validateDate(String dateString) throws ValidationException {
    Pattern p = Pattern.compile("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$");
    Matcher m = p.matcher(dateString);
    if(!m.matches()) {
      throw new ValidationException("The date entered is not valid");
    }
    return true;
  }
}
