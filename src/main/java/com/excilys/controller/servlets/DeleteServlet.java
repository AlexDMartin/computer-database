package com.excilys.controller.servlets;

import com.excilys.dao.model.Computer;
import com.excilys.service.ComputerService;
import java.util.Optional;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Delete")
public class DeleteServlet {

  @Autowired
  private ComputerService computerService;
  
  private static final Logger logger = LoggerFactory.getLogger(DeleteServlet.class);

  /**
   * Servlet doGet.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @GetMapping
  public void delete(WebRequest request, ModelAndView modelAndView) {
    try {
  
      String selection = request.getParameter("selection");
      String[] selectedId = selection.split(",");

      for (String id : selectedId) {
        Optional<Computer> computer = computerService.get(Long.parseLong(id));
        if (computer.isPresent()) {
          computerService.delete(computer.get());
        }
      }
    } catch (Exception e) {
      logger.warn(e.getMessage());
      modelAndView.setViewName("redirect:500");
    }

    modelAndView.setViewName("Dashboard");
  }
}
