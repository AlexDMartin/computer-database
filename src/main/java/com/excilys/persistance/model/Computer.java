package com.excilys.persistance.model;

import java.sql.Timestamp;

public class Computer {
	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private int companyId;

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

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int company) {
		this.companyId = company;
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
		if(this.companyId != 0) {
			result += ", from company #" + this.companyId;
		}
		return result;
	}
}
