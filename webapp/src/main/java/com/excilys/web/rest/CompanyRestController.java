package com.excilys.web.rest;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyRestController {
  
  private ComputerService computerService;
  private CompanyService companyService;
  private CompanyMapper companyMapper;

  private CompanyRestController(ComputerService computerService, CompanyService companyService,
      CompanyMapper companyMapper) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.companyMapper = companyMapper;
  }
  
  @GetMapping(value = "/companies", produces = "application/json")
  public String getAll() {
    return "{\"test\": \"test\"}";
  }
  
  @GetMapping(value = "/companies/{id}", produces = "application/json")
  public String get(@PathVariable int id) {
    return "{\"test\": \"test\"}";
  }
    
  @DeleteMapping(value = "/companies/{id}", produces = "application/json")
  public String delete(@PathVariable int id) {
    return "{\"test\": \"test\"}";
  }
  
  @PostMapping(value = "/companies/{id}", produces = "application/json")
  public String post(@PathVariable int id) {
    return "{\"test\": \"test\"}";
  }
  
  @PatchMapping(value = "/companies/{id}", produces = "application/json")
  public String patch(@PathVariable int id) {
    return "{\"test\": \"test"+ id  +"\"}";
  }
}
