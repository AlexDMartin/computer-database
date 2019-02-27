package com.excilys.dao.model;

public class CompanyBuilder {

  private int id;
  private String name;
  
  public Company build() {
    return new Company(this.id, this.name);
  }
  
  public CompanyBuilder addId(int id) {
    this.id = id;
    return this;
  }
  
  public CompanyBuilder addName(String name) {
    this.name = name;
    return this;
  }
}
