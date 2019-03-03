package com.excilys.controller.servlets;

import com.excilys.controller.PaginationController;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class IndexServlet.
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/", "/dashboard"})
public class IndexServlet extends HttpServlet {
  
  /** SerialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** ComputerService. */
  ComputerService computerService = ComputerService.getInstance();
  /** Logger. */
  Logger logger = LoggerFactory.getLogger(this.getClass());
  /** PaginationController. */
  PaginationController paginationController = PaginationController.getInstance();
  
  /**
   * Default constructor.
   */
  public IndexServlet() {
    super();
  }

  /**
   * This doGet method sends the list of computers that needs to be in the index.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String pageString = request.getParameter("page");
    String lppString = request.getParameter("lpp");
    List<Computer> computerList = null;

    if (lppString != null && lppString != "") {
      paginationController.setLimit(Integer.parseInt(lppString));
      request.setAttribute("lpp", Integer.parseInt(lppString));
    } else {
      paginationController.setLimit(10);
      request.setAttribute("lpp", 10);
    }

    if (pageString != null && pageString != "") {
      paginationController
          .setOffset((Integer.parseInt(pageString) - 1) * Integer.parseInt(lppString));
      request.setAttribute("page", Integer.parseInt(pageString));
    } else {
      paginationController.setOffset(0);
      request.setAttribute("page", 1);
    }

    try {
      computerList = computerService.getAllPaginated(paginationController);
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }

    request.setAttribute("computerList", computerList);
    request.getRequestDispatcher("view/index.jsp").forward(request, response);
  }

  /**
   * Default behaviour.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
