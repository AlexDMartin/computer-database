package com.excilys.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "computer")
public class Computer {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Nullable
  @Column(name = "introduced")
  private Date introduced;

  @Nullable
  @Column(name = "discontinued")
  private Date discontinued;

  @Nullable
  @OneToOne
  @JoinColumn(name = "company_id", nullable = true)
  private Company company;

  public Computer() {}
  
  /**
   * Instantiates a new computer.
   */
  public Computer(int id, String name, Date introduced, Date discontinued, Company company) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
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

  /*
   * (non-Javadoc)
   * 
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((company == null) ? 0 : company.hashCode());
    result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
    result = prime * result + id;
    result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
    Computer other = (Computer) obj;
    if (company == null) {
      if (other.company != null) {        
        return false;
      }
    } else if (!company.equals(other.company)) {      
      return false;
    }
    if (discontinued == null) {
      if (other.discontinued != null) {        
        return false;
      }
    } else if (!discontinued.equals(other.discontinued)) {      
      return false;
    }
    if (id != other.id) {      
      return false;
    }
    if (introduced == null) {
      if (other.introduced != null) {
        return false;        
      }
    } else if (!introduced.equals(other.introduced)) {      
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
