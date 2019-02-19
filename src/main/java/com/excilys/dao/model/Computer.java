package com.excilys.dao.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Computer.
 */
public class Computer {

  /** The id. */
  private int id;

  /** The name. */
  private String name;

  /** The introduced. */
  private Date introduced;

  /** The discontinued. */
  private Date discontinued;

  /** The company. */
  private Company company;

  /**
   * Instantiates a new computer.
   */
  public Computer() {
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the introduced.
   *
   * @return the introduced
   */
  public Date getIntroduced() {
    return introduced;
  }

  /**
   * Sets the introduced.
   *
   * @param introduced the new introduced
   */
  public void setIntroduced(Date introduced) {
    this.introduced = introduced;
  }

  /**
   * Gets the discontinued.
   *
   * @return the discontinued
   */
  public Date getDiscontinued() {
    return discontinued;
  }

  /**
   * Sets the discontinued.
   *
   * @param discontinued the new discontinued
   */
  public void setDiscontinued(Date discontinued) {
    this.discontinued = discontinued;
  }

  /**
   * Gets the company.
   *
   * @return the company
   */
  public Company getCompany() {
    return company;
  }

  /**
   * Sets the company.
   *
   * @param company the new company
   */
  public void setCompany(Company company) {
    this.company = company;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String result = this.id + "\t| " + this.name;
    if (this.introduced != null) {
      result += ", introduced: " + this.introduced;
    }
    if (this.discontinued != null) {
      result += ", discontinued: " + this.discontinued;
    }
    if (this.company != null) {
      result += ", from company " + this.company.getName();
    }
    return result;
  }
}
