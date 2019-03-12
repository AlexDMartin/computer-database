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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class SearchServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_LPP = 10;
  private static final int DEFAULT_PAGE = 1;
  private static final String DEFAULT_SORT_COLUMN = "ID";
  private static final String DEFAULT_ASCENDENCY = "DESC";
  private static Logger logger = LoggerFactory.getLogger(SearchServlet.class);

  /**
   * Servlet Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public SearchServlet() {
    super();
  }

  /**
   * Servlet doGet.
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

      int page =
          (request.getParameter("request").equals("new") || request.getParameter("page") == null
              || request.getParameter("page").isEmpty()) ? DEFAULT_PAGE
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

      String filterString = request.getParameter("filter");
      int count = 0;
      try {
        List<Computer> computerList =
            computerService.getAllSearchedPaginated(filterString, paginationController);
        request.setAttribute("computerList", computerList);
        count = computerService.countAllComputerByCriteria(filterString);
      } catch (Exception e) {
        logger.warn(e.getMessage());
        request.setAttribute("stacktrace", e.getMessage());
        request.getRequestDispatcher("view/500.jsp").forward(request, response);
        return;
      }

      request.setAttribute("filter", filterString);
      request.setAttribute("count", count);
      request.setAttribute("pageType", "search");
      request.setAttribute("paginationController", paginationController);

      request.getRequestDispatcher("view/index.jsp").forward(request, response);
    } catch (BeansException beansException) {
      logger.warn(beansException.getMessage());
    }

  }

  /**
   * Servlet doPost.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
