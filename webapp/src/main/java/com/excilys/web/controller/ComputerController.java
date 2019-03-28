package com.excilys.web.controller;

import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.ComputerDtoBuilder;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ComputerController {

  private ComputerService computerService;
  private CompanyService companyService;

  private ComputerController(ComputerService computerService, CompanyService companyService) {
    this.computerService = computerService;
    this.companyService = companyService;
  }

  /**
   * Gets only one Computer.
   * 
   * @param webRequest the WebRequest
   * @param modelAndView the ModelAndView
   * @param id the id of the computer
   * @return the ModelAndView or redirects to page 500
   */
  @GetMapping("/computer/{id}")
  public ModelAndView get(WebRequest webRequest, ModelAndView modelAndView, @PathVariable int id) {

    Optional<ComputerDto> computer = Optional.empty();
    List<CompanyDto> companies = new ArrayList<>();
    try {
      computer = computerService.find(id);
      companies = companyService.findAll();
    } catch (ServiceException serviceException) {
      return getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    if (computer.isPresent()) {
      UniquePage page = new UniquePage();
      page.setComputer(computer.get());
      page.setCompanies(companies);
      page.setViewName("editComputer");
      modelAndView.setViewName(page.getViewName());
      modelAndView.addObject("page", page);
    }

    return modelAndView;
  }

  /**
   * Gets All the computers sorted or unsorted and fills the dashboard.
   * 
   * @param webRequest the WebRequest
   * @param modelAndView the ModelAndView
   * @return the ModelAndView of the dashboard filled with either the sorted or the full list.
   */
  @GetMapping("/computers")
  public ModelAndView getAll(WebRequest webRequest, ModelAndView modelAndView) {

    ListPage page = new ListPage();

    int pageIndex =
        (webRequest.getParameter("page") == null || webRequest.getParameter("page").isEmpty())
            || (webRequest.getParameter("request") == null
                || webRequest.getParameter("request").equals("new")) ? ListPage.DEFAULT_PAGE
                    : Integer.parseInt(webRequest.getParameter("page"));

    int lpp = (webRequest.getParameter("lpp") == null || webRequest.getParameter("lpp").isEmpty())
        ? ListPage.DEFAULT_LPP
        : Integer.parseInt(webRequest.getParameter("lpp"));

    String sortColumn =
        (webRequest.getParameter("col") == null || webRequest.getParameter("col").isEmpty())
            ? ListPage.DEFAULT_SORT_COLUMN
            : webRequest.getParameter("col");

    String ascendency =
        (webRequest.getParameter("asc") == null || webRequest.getParameter("asc").isEmpty())
            ? ListPage.DEFAULT_ASCENDENCY
            : webRequest.getParameter("asc");

    String filter = webRequest.getParameter("filter");

    page.setPageIndex(pageIndex);
    page.setLinePerPage(lpp);
    page.setAscendency(ascendency);
    page.setSortColumn(sortColumn);

    List<ComputerDto> computers = new ArrayList<>();
    int count = 0;

    try {
      if (filter == null || filter.isEmpty()) {
        count = computerService.findCount();
        computers = computerService.findAllPaginated(page);

      } else {
        count = computerService.findCountByFilter(filter);
        computers = computerService.findByFilterPaginated(filter, page);
      }
    } catch (ServiceException serviceException) {
      return getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    page.setComputers(computers);
    page.setCount(count);

    modelAndView.addObject("page", page);
    modelAndView.setViewName(ListPage.VIEW_NAME);

    return modelAndView;
  }


  /**
   * Fills the creation form.
   * 
   * @param webRequest the WebRequest
   * @param modelAndView the ModelAndView
   * @return the ModelAndView of the creation form
   */
  @GetMapping("/computer")
  public ModelAndView getCreateForm(WebRequest webRequest, ModelAndView modelAndView) {
    UniquePage page = new UniquePage();

    List<CompanyDto> companies = new ArrayList<>();
    try {
      companies = companyService.findAll();
    } catch (ServiceException serviceException) {
      return this.getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    page.setCompanies(companies);
    modelAndView.setViewName("addComputer");

    modelAndView.addObject("page", page);
    return modelAndView;
  }

  /**
   * Saves a new Computer.
   * 
   * @param webRequest the webRequest
   * @param modelAndView the modelAndView
   * @return the modelAndView of a redirection
   */
  @PostMapping("/computer")
  public ModelAndView save(WebRequest webRequest, ModelAndView modelAndView) {
    ComputerDtoBuilder builder = new ComputerDtoBuilder();

    // Name
    if (webRequest.getParameter("computerName") != null
        || !webRequest.getParameter("computerName").isEmpty()) {
      builder.addName(webRequest.getParameter("computerName"));
    }

    // Introduced
    if (webRequest.getParameter("introduced") != null) {
      builder.addIntroduced(webRequest.getParameter("introduced"));
    }

    // Discontinued
    if (webRequest.getParameter("discontinued") != null) {
      builder.addDiscontinued(webRequest.getParameter("discontinued"));
    }

    // Company
    int companyId = Integer.parseInt(webRequest.getParameter("companyId"));
    Optional<CompanyDto> company = Optional.empty();
    try {
      company = companyService.find(companyId);
    } catch (ServiceException serviceException) {
      return this.getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    if (company.isPresent()) {
      builder.addCompanyDto(company.get());
    }

    // Save
    try {
      computerService.save(builder.build());
    } catch (ServiceException serviceException) {
      return this.getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    // Redirect
    return getAll(webRequest, modelAndView);
  }

  /**
   * Updates an existing computer.
   * 
   * @param webRequest the webRequest
   * @param modelAndView the modelAndView.
   * @return
   */
  @PatchMapping("/computer/{id}")
  public ModelAndView update(WebRequest webRequest, ModelAndView modelAndView) {
    ComputerDtoBuilder builder = new ComputerDtoBuilder();
    System.out.println("test");
    // Id
    if (webRequest.getParameter("id") != null || !webRequest.getParameter("id").isEmpty()) {
      builder.addId(webRequest.getParameter("id"));
    }

    // Name
    if (webRequest.getParameter("computerName") != null
        || !webRequest.getParameter("computerName").isEmpty()) {
      builder.addName(webRequest.getParameter("computerName"));
    }

    // Introduced
    if (webRequest.getParameter("introduced") != null) {
      builder.addIntroduced(webRequest.getParameter("introduced"));
    }

    // Discontinued
    if (webRequest.getParameter("discontinued") != null) {
      builder.addDiscontinued(webRequest.getParameter("discontinued"));
    }

    // Company
    int companyId = Integer.parseInt(webRequest.getParameter("companyId"));
    Optional<CompanyDto> company = Optional.empty();
    try {
      company = companyService.find(companyId);
    } catch (ServiceException serviceException) {
      return this.getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    if (company.isPresent()) {
      builder.addCompanyDto(company.get());
    }

    // Save
    try {
      computerService.save(builder.build());
    } catch (ServiceException serviceException) {
      return this.getExceptionModelAndView(modelAndView, "500", serviceException);
    }

    // Redirect
    return getAll(webRequest, modelAndView);
  }

  @DeleteMapping("/computer")
  public ModelAndView delete(WebRequest webRequest, ModelAndView modelAndView) {
    modelAndView.setViewName("index");
    return modelAndView;
  }

  /**
   * getExceptionModelAndView is used to redirect to the Exception pages.
   * 
   * @param modelAndView the current ModelAndView
   * @param viewName the view name which should correspond to the error code
   * @param exception the exception that will be displayed
   * @return The ModelAndView matching the exception that has been catched
   */
  private ModelAndView getExceptionModelAndView(ModelAndView modelAndView, String viewName,
      Exception exception) {
    modelAndView.addObject("message", exception.getMessage());
    modelAndView.setViewName("redirect:" + viewName);
    return modelAndView;
  }
}
