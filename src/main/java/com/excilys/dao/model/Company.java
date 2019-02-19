package com.excilys.dao.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Company.
 */
public class Company {

  /** The id. */
  private int id;

  /** The name. */
  private String name;

  /**
   * Instantiates a new company.
   */
  public Company() {
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String formattedString = Integer.toString(this.id) + "\t| " + this.name;
    return formattedString;
  }
}
