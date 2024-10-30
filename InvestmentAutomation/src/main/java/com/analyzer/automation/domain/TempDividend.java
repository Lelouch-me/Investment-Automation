package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "temp_dividend")
public class TempDividend {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "cash_div")
	private Double cashDiv;
	
	@Column(name = "stock_div")
	private Double stockDiv;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "year")
	private int year;
	
	public TempDividend() {
		super();
	}

	public TempDividend(String code, Double cashDiv,Double stockDiv, String date, int year) {
		super();
		
		this.code = code;
		this.cashDiv = cashDiv;
		this.stockDiv=stockDiv;
		this.date = date;
		this.year=year;
	}
	
	public TempDividend(int id,String code, Double cashDiv,Double stockDiv, String date, int year) {
		super();
		
		this.id=id;
		this.code = code;
		this.cashDiv = cashDiv;
		this.stockDiv=stockDiv;
		this.date = date;
		this.year=year;
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

	public Double getCashDiv() {
		return cashDiv;
	}

	public void setCashDiv(Double cashDiv) {
		this.cashDiv = cashDiv;
	}

	public Double getStockDiv() {
		return stockDiv;
	}

	public void setStockDiv(Double stockDiv) {
		this.stockDiv = stockDiv;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
