package com.excilys.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.excilys.exception.validation.company.InvalidIdCompanyValidationException;
import com.excilys.exception.validation.company.InvalidNameCompanyValidationException;

public class CompanyValidation {

  private static CompanyValidation companyValidationInstance = null;

  private CompanyValidation() {}

  public static CompanyValidation getInstance() {

    if (companyValidationInstance == null) {
      companyValidationInstance = new CompanyValidation();
    }

    return companyValidationInstance;
  }

  public void validateId(int id) throws InvalidIdCompanyValidationException {
    Pattern p = Pattern.compile("^-?\\d+$");
    Matcher m = p.matcher(Integer.toString(id));

    if (!m.matches()) {
      throw new InvalidIdCompanyValidationException("The company\'s id is not valid.");
    }

  }

  public void validateName(String name) throws InvalidNameCompanyValidationException {

    if (name == null || name.isEmpty()) {
      throw new InvalidNameCompanyValidationException("The company\'s name is not valid.");
    }

  }
}
