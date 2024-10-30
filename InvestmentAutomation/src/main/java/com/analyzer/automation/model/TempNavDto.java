package com.analyzer.automation.model;

public class TempNavDto {
	private int id;
	private String code;
	private Double nav;
	private String postDate;
	
	public TempNavDto() {
		super();
	}
	public TempNavDto(int id, String code, Double nav, String postDate) {
		super();
		this.id=id;
		this.code = code;
		this.nav = nav;
		this.postDate = postDate;

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
	public Double getNav() {
		return nav;
	}
	public void setNav(Double nav) {
		this.nav = nav;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
}
