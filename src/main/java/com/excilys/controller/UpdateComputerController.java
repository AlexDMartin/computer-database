package com.excilys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;
import com.excilys.view.UpdateComputerView;

/**
 * Singleton implementation of UpdateComputerController.
 */
public class UpdateComputerController {

  /** Singleton implementation of UpdateComputerController. */
  private static UpdateComputerController updateComputerControllerInstance = null;
  /** Computer Mapper. */
  ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** Company Mapper. */
  CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** View. */
  UpdateComputerView view = UpdateComputerView.getInstance();
  /** Validator. */
  Validator validator = Validator.getInstance();
  /** ComputerService. */
  ComputerService computerService = ComputerService.getInstance();
  /** CompanyService. */
  CompanyService companyService = CompanyService.getInstance();
  /** Scanner. */
  Scanner scan = new Scanner(System.in);
  /** Logger */
  Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * Singleton implementation of UpdateComputerController.
   */
  private UpdateComputerController() {
	  	ComputerDTO computerDTO = null;
	  	Computer computer = null;
	  	
	  	view.askForId();
	    int id = scan.nextInt();
	    computer = computerService.get(id).get();
	    
	    if(computer != null) {
	    	computerDTO = (ComputerDTO) computerMapper.entityToDTO(computer);
	    	try {
	    		computerDTO.setId(computerDTO.getId());
	    		
	    		view.askForNewName(computerDTO.getName());
	    		String nameInput = scan.next();
	    		validator.validateName(nameInput);
	    		computerDTO.setName(nameInput);
	    		
	    		view.askForNewIntroduced(computerDTO.getIntroduced());
	    		String introducedInput = scan.next();
	    		validator.validateDate(introducedInput);
	    		Date introducedDate = null;
	    		if(introducedInput != null) {        
	    			computerDTO.setIntroduced(introducedInput);
	    			introducedDate = new SimpleDateFormat("yyyy-MM-dd").parse(introducedInput);
	    		}
	    		
	    		view.askForNewDiscontinued(computerDTO.getDiscontinued());
	    		String discontinuedInput = scan.next();
	    		validator.validateDate(discontinuedInput);
	    		Date discontinuedDate = null;
	    		if(discontinuedInput != null) {        
	    			computerDTO.setDiscontinued(discontinuedInput);
	    			discontinuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedInput);
	    		}
	    		validator.validatePrecedence(introducedDate, discontinuedDate);
	    		
	    		view.askForNewCompany(computerDTO.getCompanyDTO());
	    		long companyInput = (long) scan.nextInt();        
	    		Optional<Company> company = companyService.get(companyInput);
	    		validator.validateCompany(company.get());
	    		CompanyDTO companyDTO = null;
	    		if(company.isPresent()) {        
	    			companyDTO = (CompanyDTO) companyMapper.entityToDTO(company.get());
	    			computerDTO.setCompanyDTO(companyDTO);
	    		}
	    		
	    		computer = computerMapper.DTOToEntity(computerDTO);
	    		
	    		scan.close();
	    	} catch (ValidationException e) {
	    		logger.warn(e.getMessage());
	    	} catch (ParseException e) {
	    		logger.warn(e.getMessage());
	    	}
	    	
	    	try {
	    		if(computer != null) {
	    			computerService.update(computer);
	    		}
	    	} catch (Exception e) {
	    		logger.warn(e.getMessage());
	    	}
	    }
  }

  /**
   * Singleton implementation of UpdateComputerController.
   *
   * @return single instance of UpdateComputerController
   */
  public static UpdateComputerController getInstance() {
    if (updateComputerControllerInstance == null) {
      updateComputerControllerInstance = new UpdateComputerController();
    }
    return updateComputerControllerInstance;
  }
}
