package com.excilys.controller.servlets;

import com.excilys.controller.PaginationController;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import java.util.List;
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
@RequestMapping("/Dashboard")
public class IndexServlet {

  @Autowired
  private ComputerService computerService;
  @Autowired
  private PaginationController paginationController;
  
  private static final int DEFAULT_LPP = 10;
  private static final int DEFAULT_PAGE = 1;
  private static final String DEFAULT_SORT_COLUMN = "ID";
  private static final String DEFAULT_ASCENDENCY = "DESC";
  private static Logger logger = LoggerFactory.getLogger(IndexServlet.class);

  /**
   * this Method shows the index.
   *
   *@return model and view with index 
   */
  @GetMapping
  public ModelAndView showIndex(WebRequest request, ModelAndView modelAndView) {
    
    try {
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

      modelAndView.addObject("count", count);
      modelAndView.addObject("computerList", computerList);
      modelAndView.addObject("paginationController", paginationController);
      modelAndView.setViewName("index");
    } catch (BeansException beansException) {
      logger.warn(beansException.getMessage());
    }
    
    return modelAndView;
  }
}
