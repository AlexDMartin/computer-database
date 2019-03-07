package com.excilys.controller.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.controller.PaginationController;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class IndexServlet.
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/", "/dashboard"})
public class IndexServlet extends HttpServlet {
  
  /** SerialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** Default line per page. */
  private static final int DEFAULT_LPP = 10;
  /** Default page. */
  private static final int DEFAULT_PAGE = 1;
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(IndexServlet.class);
  /** PaginationController. */
  private static PaginationController paginationController = PaginationController.getInstance();
  
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
    List<Computer> computerList = null;

    int page = (
        request.getParameter("page") == null || 
        request.getParameter("page").isEmpty()
        )  
            ? DEFAULT_PAGE 
            : Integer.parseInt(request.getParameter("page"));
    
    int lpp = (
        request.getParameter("lpp") == null || 
        request.getParameter("lpp").isEmpty()
        ) 
            ? DEFAULT_LPP 
            : Integer.parseInt(request.getParameter("lpp"));
    
    paginationController.setPage(page);
    paginationController.setLinePerPage(lpp);

    int count = 0;
    try {
      computerList = computerService.getAllPaginated(paginationController);
      count = computerService.countAllComputer(); 
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }

    request.setAttribute("count", count);
    request.setAttribute("computerList", computerList);
    request.setAttribute("paginationController", paginationController);
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
