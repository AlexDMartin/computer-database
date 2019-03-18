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
@RequestMapping("/Edit")
public class EditComputer {
  
  @Autowired
  private ComputerService computerService;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private ComputerMapper computerMapper;
  @Autowired
  private CompanyMapper companyMapper;
  
  private static Logger logger = LoggerFactory.getLogger(EditComputer.class);

  /**
   * This doGet methods returns information to prefill the edition form.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @GetMapping
  protected ModelAndView displayEditForm(WebRequest request, ModelAndView modelAndView) {
    try {
      long id = Long.parseLong(request.getParameter("id"));
      Optional<Computer> computer = computerService.get(id);
      List<Company> companyList = companyService.getAll();

      modelAndView.addObject("companyList", companyList);
      if (computer.isPresent()) {
        modelAndView.addObject("computer", computer.get());
      }
      modelAndView.setViewName("editComputer");
    } catch (Exception e) {
      logger.warn(e.getMessage());
      modelAndView.addObject("stacktrace", e.getMessage());
      modelAndView.setViewName("redirect:404");
    }
    return modelAndView;
  }

  /**
   * This doPost method verify data sent by the user and tries to update the database.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @PostMapping
  protected ModelAndView postEditForm(WebRequest request, ModelAndView modelAndView) {
    try {
 
      ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();

      if (request.getParameter("id") != null) {
        computerDtoBuilder.addId(request.getParameter("id"));
      }

      if (request.getParameter("companyId") != null) {
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
        computerService.update(computer);
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
