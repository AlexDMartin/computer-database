package com.excilys.dto;

public class CompanyDTOBuilder {

  private String id;
  private String name;
  
  public CompanyDTO build() {
    return new CompanyDTO(this.id, this.name);
  }
  
  public CompanyDTOBuilder addId(String id) {
    this.id = id;
    return this;
  }
  
  public CompanyDTOBuilder addName(String name) {
    this.name = name;
    return this;
  }
}
