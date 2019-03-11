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
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class AddComputer.
 */
@WebServlet(name = "Add", urlPatterns = {"/Add"})
public class AddComputer extends HttpServlet {

  /** SerialVersionUID. */
  private static final long serialVersionUID = 86529706591354229L;
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** ComputerMapper. */
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  /** CompanyService. */
  private static CompanyService companyService = CompanyService.getInstance();
  /** CompanyMapper. */
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(AddComputer.class);

  /**
   * The servlet used to add Computers.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
  }

  /**
   * This doGetMethod needs to return the company list in order to populate the scrolling list.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      List<Company> companyList = companyService.getAll();
     
      request.setAttribute("companyList", companyList);
    } catch (Exception e) {
      logger.warn(e.getMessage());
      request.setAttribute("stacktrace", e.getStackTrace());
    }

    request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
  }

  /**
   * This doPost method validates and adds the computer in the database.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();

      if (request.getParameter("companyId") != null) {
        logger.warn("companyId" + request.getParameter("companyId"));
        long companyId = Long.parseLong(request.getParameter("companyId"));
        Optional<Company> company = companyService.get(companyId);
        CompanyDto companyDto = null;
        if (company.isPresent()) {
          companyDto = (CompanyDto) companyMapper.entityToDto(company.get());
        }
        computerDtoBuilder.addCompanyDto(companyDto);
      }

      logger.warn("computerName" + request.getParameter("computerName"));
      computerDtoBuilder.addName(request.getParameter("computerName"));
      
      logger.warn("introduced" + request.getParameter("introduced"));
      if (request.getParameter("introduced") != null) {
        computerDtoBuilder.addIntroduced(request.getParameter("introduced"));
      }
      
      logger.warn("discontinued" + request.getParameter("discontinued"));
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
      request.setAttribute("stacktrace", e.getMessage());       
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
      return;
    } 

    request.getRequestDispatcher("Dashboard").forward(request, response);
  }
}
