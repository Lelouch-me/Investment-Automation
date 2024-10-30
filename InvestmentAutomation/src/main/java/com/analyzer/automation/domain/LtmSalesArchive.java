package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ltm_sales_archive")
public class LtmSalesArchive {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "ltm_sales")
	private Double ltmSales;
	
	@Column(name = "period")
	private String period;
	
	@Column(name="year")
	private int year;
	
	@Column(name = "post_date")
	private String postDate;	

	public LtmSalesArchive() {
		super();
	}

	public LtmSalesArchive(String code, Double ltmSales, String postDate, int year, String period) {
		super();
		
		
		this.code = code;
		this.ltmSales = ltmSales;
		this.postDate = postDate;
		this.year = year;
		this.period = period;
	}
	
	public LtmSalesArchive(int id,String code, Double ltmSales, String postDate, int year, String period) {
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
