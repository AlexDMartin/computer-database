package com.excilys.dto;

public class CompanyDtoBuilder {

  private String id;
  private String name;

  public CompanyDto build() {
    return new CompanyDto(this.id, this.name);
  }

  public CompanyDtoBuilder addId(String id) {
    this.id = id;
    return this;
  }

  public CompanyDtoBuilder addName(String name) {
    this.name = name;
    return this;
  }
}
