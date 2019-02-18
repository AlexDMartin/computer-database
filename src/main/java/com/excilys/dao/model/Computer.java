package com.excilys.dao.model;

import java.util.Date;

public class Computer {
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;

	public Computer() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		String result = this.id + "\t| "+ this.name;
		if(this.introduced != null) {
			result += ", introduced: " + this.introduced;
		}
		if(this.discontinued != null){
			result += ", discontinued: " + this.discontinued;
		}
		if(this.company != null) {
			result += ", from company " + this.company.getName();
		}
		return result;
	}
}
