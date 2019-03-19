package com.excilys.controller.servlets;

import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Add")
public class AddComputer {

  private static final Logger logger = LoggerFactory.getLogger(AddComputer.class);

  private ComputerService computerService;
  private CompanyService companyService;
  private ComputerMapper computerMapper;
  private CompanyMapper companyMapper;

  @Autowired
  private AddComputer(ComputerService computerService, CompanyService companyService,
      ComputerMapper computerMapper, CompanyMapper companyMapper) {
    this.computerService = computerService;
    this.companyService = companyService;
    this.computerMapper = computerMapper;
    this.companyMapper = companyMapper;
  }

  /**
   * This doGetMethod needs to return the company list in order to populate the scrolling list.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @GetMapping
  public ModelAndView displayAddForm(WebRequest request, ModelAndView modelAndView) {
    try {
      List<Company> companyList = companyService.getAll();

      modelAndView.addObject("companyList", companyList);
      modelAndView.setViewName("addComputer");
    } catch (Exception e) {
      logger.warn(e.getMessage());
      modelAndView.addObject("stacktrace", e.getStackTrace());
    }

    return modelAndView;
  }

  /**
   * This doPost method validates and adds the computer in the database.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @PostMapping
  public ModelAndView postAddForm(WebRequest request, ModelAndView modelAndView) {
    try {
      ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();
      String companyIdInput = request.getParameter("companyId");

      if (companyIdInput != null) {
        long companyId = Long.parseLong(request.getParameter("companyId"));
        Optional<Company> company = companyService.get(companyId);
        CompanyDto companyDto = null;
        if (company.isPresent()) {
          companyDto = (CompanyDto) companyMapper.entityToDto(company.get());
        }
        computerDtoBuilder.addCompanyDto(companyDto);
      }

      computerDtoBuilder.addName(request.getParameter("computerName"));

      if (request.getParameter("introduced") != null) {
        computerDtoBuilder.addIntroduced(request.getParameter("introduced"));
      }

      if (request.getParameter("discontinued") != null) {
        computerDtoBuilder.addDiscontinued(request.getParameter("discontinued"));
      }

      ComputerDto computerDto = null;
      computerDto = computerDtoBuilder.build();

      if (computerDto != null) {
        Computer computer = computerMapper.dtoToEntity(computerDto);
        computerService.save(computer);
      }
    } catch (Exception e) {
      logger.warn(e.getMessage());
      modelAndView.addObject("stacktrace", e.getMessage());
      modelAndView.setViewName("redirect:500");
    }

    modelAndView.setViewName("redirect:Dashboard");
    return modelAndView;
  }
}
