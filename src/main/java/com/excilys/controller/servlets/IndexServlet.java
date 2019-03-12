package com.excilys.controller.servlets;

import com.excilys.config.SpringConfig;
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
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(name = "Dashboard", urlPatterns = {"/", "/dashboard"})
public class IndexServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_LPP = 10;
  private static final int DEFAULT_PAGE = 1;
  private static final String DEFAULT_SORT_COLUMN = "ID";
  private static final String DEFAULT_ASCENDENCY = "DESC";
  private static Logger logger = LoggerFactory.getLogger(IndexServlet.class);

  /**
   * Default constructor.
   */
  public IndexServlet() {
    super();
  }

  /**
   * This doGet method sends the list of computers that needs to be in the index.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      ApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(SpringConfig.class);
      ComputerService computerService = applicationContext.getBean(ComputerService.class);
      PaginationController paginationController =
          applicationContext.getBean(PaginationController.class);

      List<Computer> computerList = null;

      int page = (request.getParameter("page") == null || request.getParameter("page").isEmpty())
          ? DEFAULT_PAGE
          : Integer.parseInt(request.getParameter("page"));

      int lpp = (request.getParameter("lpp") == null || request.getParameter("lpp").isEmpty())
          ? DEFAULT_LPP
          : Integer.parseInt(request.getParameter("lpp"));

      String sortColumn =
          (request.getParameter("col") == null || request.getParameter("col").isEmpty())
              ? DEFAULT_SORT_COLUMN
              : request.getParameter("col");

      String ascendency =
          (request.getParameter("asc") == null || request.getParameter("asc").isEmpty())
              ? DEFAULT_ASCENDENCY
              : request.getParameter("asc");

      paginationController.setPage(page);
      paginationController.setLinePerPage(lpp);
      paginationController.setAscendency(ascendency);
      paginationController.setSortColumn(sortColumn);

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
    } catch (BeansException beansException) {
      logger.warn(beansException.getMessage());
    }
  }

  /**
   * Default behaviour.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
