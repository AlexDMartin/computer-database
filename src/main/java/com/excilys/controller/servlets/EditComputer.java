package com.excilys.controller.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet(name = "Edit", urlPatterns = { "/Edit" })
public class EditComputer extends HttpServlet {

  /**
   * SerialVersionUID.
   */
  private static final long serialVersionUID = 9089945397283880630L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public EditComputer() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      long id = Long.parseLong(request.getParameter("id"));
      Optional<Computer> computer = ComputerService.getInstance().get(id);
      List<Company> companyList = CompanyService.getInstance().getAll();

      request.setAttribute("companyList", companyList);
      request.setAttribute("computer", computer.get());
      request.getRequestDispatcher("view/editComputer.jsp").forward(request, response);
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.setAttribute("stacktrace", e.getMessage());
      request.getRequestDispatcher("view/404.jsp").forward(request, response);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Validator validator = Validator.getInstance();
    ComputerBuilder cb = new ComputerBuilder();
    Computer computer = null;
    try {
      validator.validateId(request.getParameter("id"));
      validator.validateName(request.getParameter("computerName"));
      validator.validateDate(request.getParameter("introduced"));
      validator.validateDate(request.getParameter("discontinued"));
      
      long companyId = Long.parseLong(request.getParameter("companyId"));
      Company company = DaoFactory.getInstance().getCompanyDao().get(companyId).get();
        
      computer = cb
          .addId(Integer.parseInt(request.getParameter("id")))
          .addName(request.getParameter("computerName"))
          .addIntroduced(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("introduced")))
          .addDiscontinued(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("discontinued")))
          .addCompany(company)
          .build();
        
    } catch (ValidationException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.setAttribute("stacktrace", e.getMessage());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    } catch (ParseException e) {
      LoggerFactory.getLogger(this.getClass()).warn("Unable to parse Date");
      request.setAttribute("stacktrace", e.getMessage());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    }

    try {
      if(computer != null) {        
        ComputerService.getInstance().update(computer);
      }
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.setAttribute("stacktrace", e.getMessage());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    }

    request.getRequestDispatcher("Dashboard").forward(request, response);
  }

}
