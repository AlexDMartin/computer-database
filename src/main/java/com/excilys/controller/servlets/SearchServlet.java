package com.excilys.controller.servlets;

import com.excilys.controller.PaginationController;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Search")
public class SearchServlet {

  private static final int DEFAULT_LPP = 10;
  private static final int DEFAULT_PAGE = 1;
  private static final String DEFAULT_SORT_COLUMN = "ID";
  private static final String DEFAULT_ASCENDENCY = "DESC";
  private static Logger logger = LoggerFactory.getLogger(SearchServlet.class);

  private ComputerService computerService;
  private PaginationController paginationController;

  @Autowired
  private SearchServlet(ComputerService computerService,
      PaginationController paginationController) {
    this.computerService = computerService;
    this.paginationController = paginationController;
  }

  /**
   * Servlet doGet.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @GetMapping
  public ModelAndView search(WebRequest request, ModelAndView modelAndView) {
    try {
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
        modelAndView.addObject("computerList", computerList);
        count = computerService.countAllComputerByCriteria(filterString);
      } catch (Exception e) {
        logger.warn(e.getMessage());
        modelAndView.addObject("stacktrace", e.getMessage());
        modelAndView.setViewName("redirect:500");
        return modelAndView;
      }

      modelAndView.addObject("filter", filterString);
      modelAndView.addObject("count", count);
      modelAndView.addObject("pageType", "search");
      modelAndView.addObject("paginationController", paginationController);

      modelAndView.setViewName("index");
    } catch (BeansException beansException) {
      logger.warn(beansException.getMessage());
      modelAndView.setViewName("redirect:500");
    }

    return modelAndView;

  }
}
