package com.excilys.web.rest;

import com.excilys.dto.ComputerDto;
import com.excilys.exception.api.computer.ApiComputerException;
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
public class ComputerRestController {

  private ComputerService computerService;

  private ComputerRestController(ComputerService computerService) {
    this.computerService = computerService;
  }

  @GetMapping(value = "/computers/{id}", produces = "application/json")
  public ComputerDto get(@PathVariable int id) throws ApiComputerException {
    Optional<ComputerDto> computerDto = Optional.empty();

    try {
      computerDto = computerService.find(id);
    } catch (ServiceException serviceException) {
      throw new ApiComputerException("Get request failed\n" + serviceException.getMessage());
    }

    if (computerDto.isPresent()) {
      return computerDto.get();
    }

    return null;
  }

  @GetMapping(value = "/computers", produces = "application/json")
  public List<ComputerDto> getAll() throws ApiComputerException {
    List<ComputerDto> computers = new ArrayList<>();

    try {
      computers = computerService.findAll();
    } catch (ServiceException serviceException) {
      throw new ApiComputerException("Get request failed\n" + serviceException.getMessage());
    }

    return computers;
  }

  @DeleteMapping(value = "/computers/{id}", produces = "application/json")
  public ComputerDto delete(@PathVariable int id) throws ApiComputerException {
    Optional<ComputerDto> computerDto = Optional.empty();

    try {
      computerDto = computerService.find(id);
    } catch (ServiceException serviceException) {
      throw new ApiComputerException(
          "Get request failed (Couldn\'t find the computer that would be deleted)\n"
              + serviceException.getMessage());
    }

    if (computerDto.isPresent()) {
      try {
        computerService.delete(computerDto.get());
      } catch (ServiceException serviceException) {
        throw new ApiComputerException("Delete request failed\n" + serviceException.getMessage());
      }
      return computerDto.get();
    }
    return null;
  }

  @PostMapping(value = "/computers", produces = "application/json")
  public ResponseEntity<ComputerDto> post(@PathVariable ComputerDto computerDto)
      throws ApiComputerException {
    if (computerDto != null) {
      try {
        computerService.save(computerDto);
      } catch (ServiceException serviceException) {
        throw new ApiComputerException("Post request failed\n" + serviceException.getMessage());
      }
    }

    return new ResponseEntity<>(computerDto, HttpStatus.OK);
  }

  @PatchMapping(value = "/computers/{id}", produces = "application/json")
  public ResponseEntity<ComputerDto> patch(@PathVariable ComputerDto computerDto)
      throws ApiComputerException {
    if (computerDto != null) {
      try {
        computerService.save(computerDto);
      } catch (ServiceException serviceException) {
        throw new ApiComputerException("Patch request failed\n" + serviceException.getMessage());
      }
    }

    return new ResponseEntity<>(computerDto, HttpStatus.OK);
  }
}
