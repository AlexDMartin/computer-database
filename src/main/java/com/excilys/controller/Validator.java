package com.excilys.controller;

import java.util.Date;
import java.util.Optional;

import com.excilys.dao.model.Company;

public class Validator {

  private static Validator validatorInstance = null;

  private Validator() {
  }

  public static Validator getInstance() {
    if (validatorInstance == null) {
      validatorInstance = new Validator();
    }
    return validatorInstance;
  }

  public boolean precedence(Date introduced, Date discontinued) {
    return introduced.before(discontinued);
  }

  public boolean isValid(String date) {
    return true;
  }

  public boolean companyExists(Optional<Company> company) {
    return company.isPresent();
  }

  public boolean isYes(String input) {
    return input.charAt(0) == 'y';
  }
}
