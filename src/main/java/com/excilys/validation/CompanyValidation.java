package com.excilys.validation;

import com.excilys.exception.validation.company.InvalidIdCompanyValidationException;
import com.excilys.exception.validation.company.InvalidNameCompanyValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidation {

  private CompanyValidation() {}

  /**
   * Validates the Company's id.
   * 
   * @param id The id of a Company
   * @throws InvalidIdCompanyValidationException This exception is thrown when the Company's id is
   *         not valid
   */
  public void validateId(int id) throws InvalidIdCompanyValidationException {
    Pattern p = Pattern.compile("^-?\\d+$");
    Matcher m = p.matcher(Integer.toString(id));

    if (!m.matches()) {
      throw new InvalidIdCompanyValidationException("The company\'s id is not valid.");
    }

  }

  /**
   * Validates the Company's name.
   * 
   * @param name The name of a Company
   * @throws InvalidNameCompanyValidationException This exception is thrown when the Company's name
   *         is not valid
   */
  public void validateName(String name) throws InvalidNameCompanyValidationException {

    if (name == null || name.isEmpty()) {
      throw new InvalidNameCompanyValidationException("The company\'s name is not valid.");
    }

  }
}