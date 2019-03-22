package com.excilys.dto;

public class ComputerDto computerDto =  implements Dto {
  private String id;
  private String name;
  private String introduced;
  private String discontinued;
  private CompanyDto companyDto;

  /**
   * Constructor of a computerDto.
   * 
   * @param id the id of the computer (String)
   * @param name the name of the computer (String)
   * @param introduced the introduction date of the computer (String)
   * @param discontinued the discontinuation date of the computer (String)
   * @param companyDto the company (Dto)
   */
  public ComputerDto(String id, String name, String introduced, String discontinued,
      CompanyDto companyDto) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.companyDto = companyDto;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public CompanyDto getCompanyDto() {
    return companyDto;
  }

  public void setCompanyDto(CompanyDto companyDto) {
    this.companyDto = companyDto;
  }
}
