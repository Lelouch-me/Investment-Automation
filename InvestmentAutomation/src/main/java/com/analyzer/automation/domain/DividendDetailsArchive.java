package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dividend_details_archive")
public class DividendDetailsArchive {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "stock_div")
	private Double stockDiv;
	
	@Column(name = "cash_div")
	private Double cashDiv;
	
	@Column(name = "record_date")
	private String recordDate;
	
	@Column(name="year")
	private int year;
	

	public DividendDetailsArchive() {
		super();
	}

	public DividendDetailsArchive(String code, Double stockDiv, Double cashDiv, String recordDate, int year) {
		super();
		
		
		this.code = code;
		this.stockDiv = stockDiv;
		this.cashDiv = cashDiv;
		this.recordDate = recordDate;
		this.year = year;
	}
	
	public DividendDetailsArchive(int id,String code, Double stockDiv, Double cashDiv, String recordDate, int year) {
		super();
		
		this.id=id;
		this.code = code;
		this.stockDiv = stockDiv;
		this.cashDiv = cashDiv;
		this.recordDate = recordDate;
		this.year = year;
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
	
	public Double getStockDiv() {
		return stockDiv;
	}

	public void setStockDiv(Double stockDiv) {
		this.stockDiv = stockDiv;
	}

	public Double getCashDiv() {
		return cashDiv;
	}

	public void setCashDiv(Double cashDiv) {
		this.cashDiv = cashDiv;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
