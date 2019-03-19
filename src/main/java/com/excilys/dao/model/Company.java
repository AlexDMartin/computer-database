package com.excilys.dao.model;

/**
 * The Class Company.
 */
public class Company {

  /** The id. */
  private int id;

  /** The name. */
  private String name;

  /**
   * Controller.
   * 
   * @param id The id of the company
   * @param name The name of the company
   */
  public Company(int id, String name) {
    this.id = id;
    this.name = name;
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return Integer.toString(this.id) + "\t| " + this.name;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {      
      return true;
    }
    if (obj == null) {      
      return false;
    }
    if (getClass() != obj.getClass()) {      
      return false;
    }
    Company other = (Company) obj;
    if (id != other.id) {      
      return false;
    }
    if (name == null) {
      if (other.name != null) {        
        return false;
      }
    } else if (!name.equals(other.name)) {      
      return false;
    }
    return true;
  }
}
