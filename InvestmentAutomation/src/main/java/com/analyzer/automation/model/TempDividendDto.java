package com.analyzer.automation.model;

public class TempDividendDto {
	private int id;
	private String code;
	private Double cashDiv;
	private Double stockDiv;
	private String date;
	int year;
	public TempDividendDto() {
		super();
	}
	public TempDividendDto(int id, String code, Double cashDiv, Double stockDiv, String date,int year) {
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
