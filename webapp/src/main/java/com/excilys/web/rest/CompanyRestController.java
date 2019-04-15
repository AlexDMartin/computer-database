package com.excilys.web.rest;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dto.CompanyDto;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  
  @GetMapping(value = "/companies/{id}", produces = "application/json")
  public CompanyDto get(@PathVariable int id) {
    Optional<CompanyDto> companyDto = Optional.empty();
    
    try {
      companyDto = companyService.find(id);
    } catch (ServiceException serviceException) {
      // TODO: handle exception
    }
    
    if (companyDto.isPresent()) {      
      return companyDto.get();
    }
    
    return null;
  }
  
  @GetMapping(value = "/companies", produces = "application/json")
  public List<CompanyDto> getAll() {
    List<CompanyDto> companies = new ArrayList<>();
    
    try {
      companies = companyService.findAll();
    } catch (ServiceException serviceException) {
      // TODO: handle exception
    }
    
    return companies;
  }
    
  @DeleteMapping(value = "/companies/{id}", produces = "application/json")
  public CompanyDto delete(@PathVariable int id) {
    Optional<CompanyDto> companyDto = Optional.empty();
    
    try {
      companyDto = companyService.find(id);
    } catch (ServiceException serviceException) {
      // TODO: handle exception
    }
    
    if(companyDto.isPresent()) {      
      try {
        companyService.delete(companyDto.get());
      } catch (ServiceException serviceException) {
        // TODO: handle exception
      }
      return companyDto.get();
    }
    return null;
  }
  
  @PostMapping(value = "/companies", produces = "application/json")
  public ResponseEntity<CompanyDto> post(@PathVariable CompanyDto companyDto) {
      if (companyDto != null) {
        try {
          companyService.save(companyDto);
        } catch (ServiceException serviceException) {
          // TODO: handle exception
        }
      }
      
      return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.OK);
  }
  
  @PatchMapping(value = "/companies/{id}", produces = "application/json")
  public ResponseEntity<CompanyDto> patch(@PathVariable CompanyDto companyDto) {
    if (companyDto != null) {
      try {
        companyService.save(companyDto);
      } catch (ServiceException serviceException) {
        // TODO: handle exception
      }
    }
    
    return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.OK);
  }
}
