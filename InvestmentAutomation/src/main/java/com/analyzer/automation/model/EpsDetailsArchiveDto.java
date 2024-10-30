package com.analyzer.automation.model;

public class EpsDetailsArchiveDto {
	private int id;
	private String code;
	private Double eps;
	private String postDate;
	private int year;
	private String period;
	public EpsDetailsArchiveDto() {
		super();
	}
	public EpsDetailsArchiveDto(int id, String code, Double eps, String postDate, int year, String period) {
		super();
		this.id=id;
		this.code = code;
		this.eps = eps;
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
	public Double getEps() {
		return eps;
	}
	public void setEps(Double eps) {
		this.eps = eps;
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
