package com.excilys.controller.servlets;

import com.excilys.controller.PaginationController;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class SearchServlet.
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class SearchServlet extends HttpServlet {

  /** SerialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** Default line per page. */
  private static final int DEFAULT_LPP = 10;
  /** Default page. */
  private static final int DEFAULT_PAGE = 1;
  /** Default sort column. */
  private static final String DEFAULT_SORT_COLUMN = "ID";
  /** Default ascendency. */
  private static final String DEFAULT_ASCENDENCY = "DESC";
  /** ComputerService. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** Pagination Controller. */
  private static PaginationController paginationController = PaginationController.getInstance();
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(SearchServlet.class);

  /**
   * Servlet Constructor.
   * @see HttpServlet#HttpServlet()
   */
  public SearchServlet() {
    super();
  }

  /**
   * Servlet doGet.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<Computer> computerList = new ArrayList<Computer>();

    int page = (request.getParameter("request") == "new" || request.getParameter("page") == null
        || request.getParameter("page").isEmpty()) ? DEFAULT_PAGE
            : Integer.parseInt(request.getParameter("page"));

    int lpp =
        (request.getParameter("lpp") == null || request.getParameter("lpp").isEmpty()) ? DEFAULT_LPP
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
      computerList = computerService.getAllSearchedPaginated(filterString, paginationController);
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
    request.setAttribute("computerList", computerList);
    request.getRequestDispatcher("view/index.jsp").forward(request, response);
  }

  /**
   * Servlet doPost.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

}
