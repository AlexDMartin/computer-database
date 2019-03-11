package com.excilys.controller;

/**
 * Creates a new Pagination Controller.
 */
public class PaginationController {

  private static PaginationController paginationControllerInstance = null;

  private int page;
  private int linePerPage;

  private int limit;
  private int offset;

  private int previous;
  private int[] pages = new int[5];
  private int next;

  private String sortColumn = "ID";
  private String ascendency = "ASC";


  /**
   * Getter for line Per Page.
   * 
   * @return the limit
   */
  public int getLinePerPage() {
    return linePerPage;
  }

  /**
   * Setter for Line Per Page.
   * 
   * @param linePerPage the line per page to set
   */
  public void setLinePerPage(int linePerPage) {
    this.offset = linePerPage * (this.page - 1);
    this.limit = linePerPage;
    this.linePerPage = linePerPage;
  }

  /**
   * Getter for page.
   * 
   * @return the page
   */
  public int getPage() {
    return page;
  }

  /**
   * Setter for page.
   * 
   * @param page the page to set
   */
  public void setPage(int page) {
    if (page == 1) {
      this.previous = page;
      pages[0] = page;
      pages[1] = page + 1;
      pages[2] = page + 2;
      pages[3] = page + 3;
      pages[4] = page + 4;
      this.next = page + 1;
    } else {
      if (page == 2) {
        previous = page - 1;
        pages[0] = page - 1;
        pages[1] = page;
        pages[2] = page + 1;
        pages[3] = page + 2;
        pages[4] = page + 3;
        next = page + 1;
      } else {
        previous = page - 1;
        pages[0] = page - 2;
        pages[1] = page - 1;
        pages[2] = page;
        pages[3] = page + 1;
        pages[4] = page + 2;
        next = page + 1;
      }
    }

    this.page = page;
  }

  /**
   * Getter for the offset.
   * 
   * @return the Offset
   */
  public int getOffset() {
    return offset;
  }

  /**
   * Getter for the limit.
   * 
   * @return the limit
   */
  public int getLimit() {
    return limit;
  }

  /**
   * Getter for the pages.
   * 
   * @return an array that have 5 elements
   */
  public int[] getPages() {
    return pages;
  }

  /**
   * Getter for the previous page.
   * 
   * @return the previous page number
   */
  public int getPrevious() {
    return previous;
  }

  /**
   * Getter for the next page.
   * 
   * @return the next page number
   */
  public int getNext() {
    return next;
  }

  /**
   * Getter for the sort column.
   * 
   * @return the column on which the sorting applies
   */
  public String getSortColumn() {
    return sortColumn;
  }

  /**
   * Setter for the sort column. switches the ascendency on second call on the same column
   * 
   * @param sortColumn The column by which the user sorts 
   */
  public void setSortColumn(String sortColumn) {
    this.sortColumn = sortColumn;
  }

  /**
   * Switches the ascendency from "ASC" to "DESC" and from "DESC" to "ASC".
   */
  public String getInvertedAscendency(String ascendency) {
    if (ascendency.equals("ASC")) {
      return "DESC";
    }
    return "ASC";
  }

  /**
   * Setter for ascendency.
   * 
   * @param ascendency Whether or not the sort should be ascending or descending
   */
  public void setAscendency(String ascendency) {
    this.ascendency = ascendency;
  }

  /**
   * Getter for the ascendency.
   * 
   * @return the ascendency of the column on which the sorting applies
   */
  public String getAscendency() {
    return ascendency;
  }

  private PaginationController() {}

  /**
   * Singleton implementation of PaginationController.
   * 
   * @return single instance of PaginationController
   */
  public static PaginationController getInstance() {
    if (paginationControllerInstance == null) {
      paginationControllerInstance = new PaginationController();
    }
    return paginationControllerInstance;

  }
}
