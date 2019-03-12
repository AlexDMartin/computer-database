package com.excilys.controller.servlets;

import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class DeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 7210607623729089403L;
  @Autowired
  private ComputerService computerService;
  private static final Logger logger = LoggerFactory.getLogger(DeleteServlet.class);

  /**
   * Servlet Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public DeleteServlet() {
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
    String selection = request.getParameter("selection");
    String[] selectedId = selection.split(",");
    try {
      for (String id : selectedId) {
        Optional<Computer> computer = computerService.get(Long.parseLong(id));
        if (computer.isPresent()) {
          computerService.delete(computer.get());
        }
      }
    } catch (Exception e) {
      logger.warn(e.getMessage());
      request.getRequestDispatcher("view/500.jsp").forward(request, response);
      return;
    }

    request.getRequestDispatcher("Dashboard").forward(request, response);
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
