package com.excilys.dao.model;

import java.util.Date;

public class ComputerBuilder {

  private int id;
  private String name;
  private Date introduced;
  private Date discontinued;
  private Company company;
  
  public Computer build() {
    return new Computer(this.id, this.name, this.introduced, this.discontinued, this.company);
  }
  
  public ComputerBuilder addId(int id) {
    this.id = id;
    return this;
  }
  
  public ComputerBuilder addName(String name) {
    this.name = name;
    return this;
  }
  
  public ComputerBuilder addIntroduced(Date introduced) {
    this.introduced = introduced;
    return this;
  }
  
  public ComputerBuilder addDiscontinued(Date discontinued) {
    this.discontinued = discontinued;
    return this;
  }
  
  public ComputerBuilder addCompany(Company company) {
    this.company = company;
    return this;
  }
  
}
