package com.excilys.web.rest;

import com.excilys.dao.mappers.ComputerMapper;
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
public class ComputerRestController {

  private ComputerService computerService;
  private CompanyService companyService;
  private ComputerMapper computerMapper;

  private ComputerRestController(ComputerService computerService, CompanyService companyService,
      ComputerMapper computerMapper) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.computerMapper = computerMapper;
  }

  @GetMapping(value = "/computers/{id}", produces = "application/json")
  public String get(@PathVariable int id) {
    return "{\"test\": \"test\"}";
  }
  
  @GetMapping(value = "/computers", produces = "application/json")
  public String getAll() {
    return "{\"test\": \"test\"}";
  }
  
  @DeleteMapping(value = "/computers/{id}", produces = "application/json")
  public String delete(@PathVariable int id) {
    return "{\"test\": \"test\"}";
  }
  
  @PostMapping(value = "/computers", produces = "application/json")
  public String post(@PathVariable int id) {
    return "{\"test\": \"test\"}";
  }
  
  @PatchMapping(value = "/computers/{id}", produces = "application/json")
  public String patch(@PathVariable int id) {
    return "{\"test\": \"test"+ id  +"\"}";
  }
}
