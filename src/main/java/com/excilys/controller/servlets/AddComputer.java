package com.excilys.controller.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import org.slf4j.LoggerFactory;

import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet(name = "Add", urlPatterns = { "/Add" })
public class AddComputer extends HttpServlet {

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Company> companyList = null;
    try {
      companyList = CompanyService.getInstance().getAll();
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.setAttribute("stacktrace", e.getStackTrace());
    }

    request.setAttribute("companyList", companyList);
    request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Validator validator = Validator.getInstance();
    Computer computer = new Computer();
    try {
      validator.validateName(request.getParameter("computerName"));
      computer.setName(request.getParameter("computerName"));

      validator.validateReversedDate(request.getParameter("introduced"));
      computer.setIntroduced(
          new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("introduced")));

      validator.validateReversedDate(request.getParameter("discontinued"));
      computer.setDiscontinued(
          new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("discontinued")));

      long companyId = Long.parseLong(request.getParameter("companyId"));
      Company company = CompanyService.getInstance().get(companyId).get();
      computer.setCompany(company);
    } catch (ValidationException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.setAttribute("stacktrace", e.getStackTrace());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    } catch (ParseException e) {
      LoggerFactory.getLogger(this.getClass()).warn("Unable to parse Date");
      request.setAttribute("stacktrace", e.getStackTrace());      
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    }

    try {
      ComputerService.getInstance().save(computer);
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.setAttribute("stacktrace", e.getStackTrace());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    }

    request.getRequestDispatcher("Dashboard").forward(request, response);
  }

}