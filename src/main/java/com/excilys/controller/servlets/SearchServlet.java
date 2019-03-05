package com.excilys.controller.servlets;

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
import com.excilys.controller.PaginationController;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class SearchServlet extends HttpServlet {
  
    /** SerialVersionUID. */
	private static final long serialVersionUID = 1L;
    /** ComputerService. */
	private static ComputerService computerService = ComputerService.getInstance();
    /** Pagination Controller. */
	private static PaginationController paginationController = PaginationController.getInstance();
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SearchServlet.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String lppString = request.getParameter("lpp");
      String filterString = request.getParameter("filter");
      String pageString = request.getParameter("page");
      
      if (lppString != null && lppString != "") {
        paginationController.setLimit(Integer.parseInt(lppString));
        request.setAttribute("lpp", Integer.parseInt(lppString));
      } else {
        paginationController.setLimit(10);
        request.setAttribute("lpp", 10);
      }
      
      if (request.getParameter("pageType") == "search" && pageString != null && pageString != "") {
        paginationController
            .setOffset((Integer.parseInt(pageString) - 1) * Integer.parseInt(lppString));
        request.setAttribute("page", Integer.parseInt(pageString));
      } else {
        paginationController.setOffset(0);
        request.setAttribute("page", 1);
      }
      
      List<Computer> computerList = new ArrayList<Computer>(); 
      try {
        computerList = computerService.getAllSearchedPaginated(filterString, paginationController);
      } catch (Exception e) {
        logger.warn(e.getMessage());
      }
      
      request.setAttribute("pageType", "search");
      request.setAttribute("filter", filterString);
      request.setAttribute("computerList", computerList);
      request.getRequestDispatcher("view/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
