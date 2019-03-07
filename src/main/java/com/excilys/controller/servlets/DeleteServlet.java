package com.excilys.controller.servlets;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class DeleteServlet extends HttpServlet {
  
  /** SerialVersionUid. */
  private static final long serialVersionUID = 7210607623729089403L;
  /** Computer Service. */
  private static ComputerService computerService = ComputerService.getInstance();
  /** Logger. */
  private static Logger logger = LoggerFactory.getLogger(DeleteServlet.class);

  /**
   * @see HttpServlet#HttpServlet()
   */
  public DeleteServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String selection = request.getParameter("selection");
    String[] selectedId = selection.split(",");
    try {
      for(String id: selectedId) {
        Optional<Computer> computer = computerService.get(Long.parseLong(id));
        if(computer.isPresent())
        computerService.delete(computer.get());
      }
    } catch (Exception e) {
       logger.warn(e.getMessage());
       request.getRequestDispatcher("view/500.jsp").forward(request, response);
       return;
    }

    request.getRequestDispatcher("Dashboard").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
