package com.excilys.validation;

import com.excilys.dao.model.Company;
import com.excilys.exception.validation.company.CompanyValidationException;
import com.excilys.exception.validation.company.InvalidIdCompanyValidationException;
import com.excilys.exception.validation.company.InvalidNameCompanyValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidation {

  private CompanyValidation() {}
  
  /**
   * Validate a Company.
   * 
   * @param company the company that will be checked
   * @throws CompanyValidationException the errors that will be throwed
   */
  public void validate(Company company) throws CompanyValidationException {
    this.validateId(company.getId());
    this.validateName(company.getName());
  }

  /**
   * Validates the Company's id.
   * 
   * @param id The id of a Company
   * @throws InvalidIdCompanyValidationException This exception is thrown when the Company's id is
   *         not valid
   */
  private void validateId(int id) throws InvalidIdCompanyValidationException {
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
  private void validateName(String name) throws InvalidNameCompanyValidationException {

    if (name == null || name.isEmpty()) {
      throw new InvalidNameCompanyValidationException("The company\'s name is not valid.");
    }

  }
}
