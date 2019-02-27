package com.excilys.dto;

public class ComputerDTOBuilder {

  private String id;
  private String name;
  private String introduced;
  private String discontinued;
  private String company;
    
 public ComputerDTO build() {
   return new ComputerDTO(this.id, this.name, this.introduced, this.discontinued, this.company);
 }
 
 public ComputerDTOBuilder addId(String id) {
   this.id = id;
   return this;
 }
 
 public ComputerDTOBuilder addName(String name) {
   this.name = name;
   return this;
 }
 
 public ComputerDTOBuilder addIntroduced(String introduced) {
   this.introduced = introduced;
   return this;
 }
 
 public ComputerDTOBuilder addDiscontinued(String discontinued) {
   this.discontinued = discontinued;
   return this;
 }
 
 public ComputerDTOBuilder addCompany(String company) {
   this.company = company;
   return this;
 }
  
}
