package com.excilys.web.rest;

import com.excilys.api.company.ApiCompanyException;
import com.excilys.dto.CompanyDto;
import com.excilys.service.CompanyService;
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

  private CompanyService companyService;

  private CompanyRestController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping(value = "/companies/{id}", produces = "application/json")
  public CompanyDto get(@PathVariable int id) throws ApiCompanyException {
    Optional<CompanyDto> companyDto = Optional.empty();

    try {
      companyDto = companyService.find(id);
    } catch (ServiceException serviceException) {
      throw new ApiCompanyException("Get request failed\n" + serviceException.getMessage());
    }

    if (companyDto.isPresent()) {
      return companyDto.get();
    }

    return null;
  }

  @GetMapping(value = "/companies", produces = "application/json")
  public List<CompanyDto> getAll() throws ApiCompanyException {
    List<CompanyDto> companies = new ArrayList<>();

    try {
      companies = companyService.findAll();
    } catch (ServiceException serviceException) {
      throw new ApiCompanyException("Get request failed\n" + serviceException.getMessage());
    }

    return companies;
  }

  @DeleteMapping(value = "/companies/{id}", produces = "application/json")
  public CompanyDto delete(@PathVariable int id) throws ApiCompanyException {
    Optional<CompanyDto> companyDto = Optional.empty();

    try {
      companyDto = companyService.find(id);
    } catch (ServiceException serviceException) {
      throw new ApiCompanyException(
          "Get request failed (Couldn\'t find the company that would be deleted)\n"
              + serviceException.getMessage());
    }

    if (companyDto.isPresent()) {
      try {
        companyService.delete(companyDto.get());
      } catch (ServiceException serviceException) {
        throw new ApiCompanyException("Delete request failed\n" + serviceException.getMessage());
      }
      return companyDto.get();
    }
    return null;
  }

  @PostMapping(value = "/companies", produces = "application/json")
  public ResponseEntity<CompanyDto> post(@PathVariable CompanyDto companyDto)
      throws ApiCompanyException {
    if (companyDto != null) {
      try {
        companyService.save(companyDto);
      } catch (ServiceException serviceException) {
        throw new ApiCompanyException("Post request failed\n" + serviceException.getMessage());
      }
    }

    return new ResponseEntity<>(companyDto, HttpStatus.OK);
  }

  @PatchMapping(value = "/companies/{id}", produces = "application/json")
  public ResponseEntity<CompanyDto> patch(@PathVariable CompanyDto companyDto)
      throws ApiCompanyException {
    if (companyDto != null) {
      try {
        companyService.save(companyDto);
      } catch (ServiceException serviceException) {
        throw new ApiCompanyException("Patch request failed\n" + serviceException.getMessage());
      }
    }

    return new ResponseEntity<>(companyDto, HttpStatus.OK);
  }
}
