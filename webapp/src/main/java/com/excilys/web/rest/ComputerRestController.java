package com.excilys.web.rest;

import com.excilys.dto.ComputerDto;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  private ComputerRestController(ComputerService computerService) {
    this.computerService = computerService;
  }

  @GetMapping(value = "/computers/{id}", produces = "application/json")
  public ComputerDto get(@PathVariable int id) {
    Optional<ComputerDto> computerDto = Optional.empty();
    
    try {
      computerDto = computerService.find(id);
    } catch (ServiceException serviceException) {
      // TODO: handle exception
    }
    
    if (computerDto.isPresent()) {      
      return computerDto.get();
    }
    
    return null;
  }
  
  @GetMapping(value = "/computers", produces = "application/json")
  public List<ComputerDto> getAll() {
    List<ComputerDto> computers = new ArrayList<>();
    
    try {
      computers = computerService.findAll();
    } catch (ServiceException serviceException) {
      // TODO: handle exception
    }
    
    return computers;
  }
  
  @DeleteMapping(value = "/computers/{id}", produces = "application/json")
  public ComputerDto delete(@PathVariable int id) {
    Optional<ComputerDto> computerDto = Optional.empty();
    
    try {
      computerDto = computerService.find(id);
    } catch (ServiceException serviceException) {
      // TODO: handle exception
    }
    
    if(computerDto.isPresent()) {      
      try {
        computerService.delete(computerDto.get());
      } catch (ServiceException serviceException) {
        // TODO: handle exception
      }
      return computerDto.get();
    }
    return null;
  }
  
  @PostMapping(value = "/computers", produces = "application/json")
  public String post(@PathVariable int id) {
    return "";
  }
  
  @PatchMapping(value = "/computers/{id}", produces = "application/json")
  public String patch(@PathVariable int id) {
    return "{\"test\": \"test"+ id  +"\"}";
  }
}
