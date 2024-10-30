package com.analyzer.automation.model;

public class DividendDetailsArchiveDto {
	private int id;
	private String code;
	private Double stockDiv;
	private Double cashDiv;
	private String recordDate;
	private int year;
	public DividendDetailsArchiveDto() {
		super();
	}
	public DividendDetailsArchiveDto(int id, String code, Double stockDiv, Double cashDiv, String recordDate, int year) {
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
