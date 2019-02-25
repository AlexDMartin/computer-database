package com.excilys.controller.servlets;

import java.io.IOException;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

=======
>>>>>>> Integrating web layout and fixing JDBC issues
=======
import java.text.SimpleDateFormat;
import java.util.List;

>>>>>>> Implementing Adding form
=======
import java.text.SimpleDateFormat;
import java.util.List;

>>>>>>> origin/web-ui
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
<<<<<<< HEAD
import javax.xml.bind.ValidationException;

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.validator.Validator;
=======
>>>>>>> Integrating web layout and fixing JDBC issues
=======
>>>>>>> origin/web-ui

import org.slf4j.LoggerFactory;

import com.excilys.dao.DaoFactory;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.Computer;

/**
 * Servlet implementation class AddComputer
 */
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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

    Validator validator = Validator.getInstance();
    Computer computer = new Computer();
    try {
      validator.validateName(request.getParameter("computerName"));
      computer.setName(request.getParameter("computerName"));

      validator.validateDate(request.getParameter("introduced"));
      computer.setIntroduced(
          new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("introduced")));

      validator.validateDate(request.getParameter("discontinued"));
      computer.setDiscontinued(
          new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("discontinued")));

      long companyId = Long.parseLong(request.getParameter("companyId"));
      Company company = DaoFactory.getInstance().getCompanyDao().get(companyId).get();
      computer.setCompany(company);
    } catch (ValidationException e) {
      LoggerFactory.getLogger(this.getClass()).warn(e.getMessage());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    } catch (ParseException e) {
      LoggerFactory.getLogger(this.getClass()).warn("Unable to parse Date");
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
    }

    // Add
    try {
      ComputerService.getInstance().save(computer);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
  }
=======
@WebServlet(name="Add", urlPatterns = {"/Add"})
=======
@WebServlet(name = "Add", urlPatterns = { "/Add" })
>>>>>>> Implementing Adding form
=======
@WebServlet(name = "Add", urlPatterns = { "/Add" })
>>>>>>> origin/web-ui
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

<<<<<<< HEAD
<<<<<<< HEAD
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
>>>>>>> Integrating web layout and fixing JDBC issues
=======
=======
>>>>>>> origin/web-ui
    
    // Add
    LoggerFactory.getLogger(this.getClass()).warn(computer.toString());// test 
    DaoFactory.getInstance().getComputerDao().save(computer);
    
    request.getRequestDispatcher("view/addComputer.jsp").forward(request, response);
  }
<<<<<<< HEAD
>>>>>>> Implementing Adding form
=======
>>>>>>> origin/web-ui

}
