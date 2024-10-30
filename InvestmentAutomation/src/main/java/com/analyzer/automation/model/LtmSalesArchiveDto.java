package com.analyzer.automation.model;

public class LtmSalesArchiveDto {
	private int id;
	private String code;
	private Double ltmSales;
	private String postDate;
	private int year;
	private String period;
	public LtmSalesArchiveDto() {
		super();
	}
	public LtmSalesArchiveDto(int id, String code, Double ltmSales, String postDate, int year, String period) {
		super();
		this.id=id;
		this.code = code;
		this.ltmSales = ltmSales;
		this.postDate = postDate;
		this.year = year;
		this.period = period;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getLtmSales() {
		return ltmSales;
	}
	public void setLtmSales(Double ltmSales) {
		this.ltmSales = ltmSales;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}	
}
