package com.excilys.web.controller;

import com.excilys.dto.ComputerDto;
import java.util.List;

public class ListPage {

  protected static final int DEFAULT_LPP = 10;
  protected static final int DEFAULT_PAGE = 1;
  protected static final String DEFAULT_SORT_COLUMN = "ID";
  protected static final String DEFAULT_ASCENDENCY = "DESC";
  protected static final String VIEW_NAME = "index";
  
  private int pageIndex = 1;
  private List<ComputerDto> computers;
  
  private int limit;
  private int offset;

  private int previous;
  private int[] pages = new int[5];
  private int next;

  private String sortColumn = "ID";
  private String ascendency = "ASC";
  
  private int linePerPage;
  private int count;

  public List<ComputerDto> getComputers() {
    return computers;
  }

  public void setComputers(List<ComputerDto> computers) {
    this.computers = computers;
  }
  
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
    this.offset = linePerPage * (this.pageIndex - 1);
    this.limit = linePerPage;
    this.linePerPage = linePerPage;
  }

  /**
   * Getter for page.
   * 
   * @return the page
   */
  public int getPageIndex() {
    return pageIndex;
  }

  /**
   * Setter for page.
   * 
   * @param pageIndex the page to set
   */
  public void setPageIndex(int pageIndex) {
    if (pageIndex == 1) {
      this.previous = pageIndex;
      pages[0] = pageIndex;
      pages[1] = pageIndex + 1;
      pages[2] = pageIndex + 2;
      pages[3] = pageIndex + 3;
      pages[4] = pageIndex + 4;
      this.next = pageIndex + 1;
    } else {
      if (pageIndex == 2) {
        previous = pageIndex - 1;
        pages[0] = pageIndex - 1;
        pages[1] = pageIndex;
        pages[2] = pageIndex + 1;
        pages[3] = pageIndex + 2;
        pages[4] = pageIndex + 3;
        next = pageIndex + 1;
      } else {
        previous = pageIndex - 1;
        pages[0] = pageIndex - 2;
        pages[1] = pageIndex - 1;
        pages[2] = pageIndex;
        pages[3] = pageIndex + 1;
        pages[4] = pageIndex + 2;
        next = pageIndex + 1;
      }
    }

    this.pageIndex = pageIndex;
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

  public int getCount() {
    return this.count;
  }
  
  public void setCount(int count) {
    this.count = count;
  }

}
