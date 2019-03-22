package com.excilys.controller.servlets;

import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

  private ComputerService computerService;
  private CompanyService companyService;

  @Autowired
  private AddComputer(ComputerService computerService, CompanyService companyService) {
    this.computerService = computerService;
    this.companyService = companyService;
  }

  /**
   * This doGetMethod needs to return the company list in order to populate the scrolling list.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @GetMapping
  public ModelAndView displayAddForm(WebRequest request, ModelAndView modelAndView) {
    try {
      List<CompanyDto> companyList = companyService.getAll();

      modelAndView.addObject("companyList", companyList);
      modelAndView.setViewName("addComputer");
    } catch (ServiceException serviceException) {
      modelAndView.addObject("stacktrace", serviceException.getStackTrace());
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
        int companyId = Integer.parseInt(request.getParameter("companyId"));
        Optional<CompanyDto> company = companyService.get(companyId);
        CompanyDto companyDto = null;
        if (company.isPresent()) {
          companyDto = company.get();
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
        computerService.save(computerDto);
      }
    } catch (ServiceException serviceException) {
      modelAndView.addObject("stacktrace", serviceException.getMessage());
      modelAndView.setViewName("redirect:500");
    }

    modelAndView.setViewName("redirect:Dashboard");
    return modelAndView;
  }
}
