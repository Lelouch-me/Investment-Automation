package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "temp_nav")
public class TempNav {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "nav")
	private Double nav;
	
	@Column(name = "date")
	private String date;
	

	public TempNav() {
		super();
	}

	public TempNav(String code, Double nav, String date) {
		super();
		
		
		this.code = code;
		this.nav = nav;
		this.date = date;
	}
	
	public TempNav(int id,String code, Double nav, String date) {
		super();
		
		this.id=id;
		this.code = code;
		this.nav = nav;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
