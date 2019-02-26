package com.excilys.controller;

/**
 *  Creates a new Pagination Controller.
 */
public class PaginationController {

  private static PaginationController paginationControllerInstance = null;
  
  private int limit;
  private int offset;
  
  private PaginationController() {}
  
  public static PaginationController getInstance() {
    if(paginationControllerInstance == null) {
      paginationControllerInstance = new PaginationController();
    }
    return paginationControllerInstance;
    
  }

  /**
   * @return the limit
   */
  public int getLimit() {
    return limit;
  }

  /**
   * @param limit the limit to set
   */
  public void setLimit(int limit) {
    this.limit = limit;
  }

  /**
   * @return the offset
   */
  public int getOffset() {
    return offset;
  }

  /**
   * @param offset the offset to set
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }

}
