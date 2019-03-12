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
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servlet implementation class AddComputer.
 */
@WebServlet(name = "Add", urlPatterns = {"/Add"})
public class AddComputer extends HttpServlet {

  private static final long serialVersionUID = 86529706591354229L;
  @Autowired
  private ComputerService computerService;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private ComputerMapper computerMapper;
  @Autowired
  private CompanyMapper companyMapper;
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
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
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
      request.setAttribute("stacktrace", e.getMessage());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
      return;
    }

    request.getRequestDispatcher("Dashboard").forward(request, response);
  }
}
