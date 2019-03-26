package com.excilys.dto;

public class ComputerDtoBuilder {

  private String id;
  private String name;
  private String introduced;
  private String discontinued;
  private CompanyDto companyDto;

  public ComputerDto build() {
    return new ComputerDto(this.id, this.name, this.introduced, this.discontinued, this.companyDto);
  }

  public ComputerDtoBuilder addId(String id) {
    this.id = id;
    return this;
  }

  public ComputerDtoBuilder addName(String name) {
    this.name = name;
    return this;
  }

  public ComputerDtoBuilder addIntroduced(String introduced) {
    this.introduced = introduced;
    return this;
  }

  public ComputerDtoBuilder addDiscontinued(String discontinued) {
    this.discontinued = discontinued;
    return this;
  }

  public ComputerDtoBuilder addCompanyDto(CompanyDto companyDto) {
    this.companyDto = companyDto;
    return this;
  }
}
