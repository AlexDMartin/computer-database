package com.excilys.web.controller;

import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import java.util.List;

public class UniquePage {

  private ComputerDto computer;
  private List<CompanyDto> companies;
  private String viewName; 
  
  
  public ComputerDto getComputer() {
    return this.computer;
  }
  
  public void setComputer(ComputerDto computer) {
    this.computer = computer;
  }
  
  public String getViewName() {
    return this.viewName;
  }

  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

  public List<CompanyDto> getCompanies() {
    return this.companies;
  }

  public void setCompanies(List<CompanyDto> companies) {
    this.companies = companies;
  }

}
