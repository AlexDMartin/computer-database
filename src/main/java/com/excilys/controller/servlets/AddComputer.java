package com.excilys.controller.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet(name = "Add", urlPatterns = { "/Add" })
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

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
      companyList = DaoFactory.getInstance().getCompanyDao().getAll();
    } catch (Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }

    request.setAttribute("companyList", companyList);
    request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Form validation
    Computer computer = new Computer();
    // Name
    computer.setName(request.getParameter("computerName"));
    // Introduced
    try {
      if(request.getParameter("introduced") != null) {
        computer.setIntroduced( new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("introduced")));
      }
    } catch(Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
    // Discontinued
    try{
      if(request.getParameter("discontinued") != null) {
        computer.setDiscontinued( new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("discontinued")));
      }
    } catch(Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
    // Company
    Company company = null;
    LoggerFactory.getLogger(this.getClass()).warn(request.getParameter("companyId"));// test
    long companyId = Long.parseLong(request.getParameter("companyId"));
    try { 
      company = DaoFactory.getInstance().getCompanyDao().get(companyId).get();
    }catch(Exception e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
    }
    computer.setCompany(company);

    
    // Add
    LoggerFactory.getLogger(this.getClass()).warn(computer.toString());// test 
    DaoFactory.getInstance().getComputerDao().save(computer);
    
    request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
  }

}
