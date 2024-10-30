package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "eps_details_archive")
public class EpsDetailsArchive {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "eps")
	private Double eps;
	
	@Column(name = "post_date")
	private String postDate;
	
	@Column(name="year")
	private int year;
	
	@Column(name = "period")
	private String period;

	public EpsDetailsArchive() {
		super();
	}

	public EpsDetailsArchive(String code, Double eps, String postDate, int year, String period) {
		super();
		
		
		this.code = code;
		this.eps = eps;
		this.postDate = postDate;
		this.year = year;
		this.period = period;
	}
	
	public EpsDetailsArchive(int id,String code, Double eps, String postDate, int year, String period) {
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
