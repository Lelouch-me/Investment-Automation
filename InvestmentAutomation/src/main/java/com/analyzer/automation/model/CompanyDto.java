package com.analyzer.automation.model;

public class CompanyDto {
	private String code;
	private String companyName;
	
	public CompanyDto() {
		super();
	}
	
	public CompanyDto(String code, String companyName) {
		this.code = code;
		this.companyName=companyName;
		
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
