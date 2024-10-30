package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fund_summary")
public class FundSummary {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "code_type")
	private String codeType;
	
	@Column(name = "avg_cost")
	private Double avgCost;
	
	@Column(name = "ltp")
	private Double ltp;
	
	@Column(name = "out_share", nullable = true)
	private Double outShare;
	
	@Column(name = "fund")
	private String fund;
	
	@Column(name = "fund_share", nullable = true)
	private Double fundShare;
	
	@Column(name = "cost_value")
	private Double costValue;
	
	@Column(name = "market_cost")
	private Double marketCost;

	public FundSummary() {
		super();
	}

	public FundSummary(int id, String code, String codeType, Double avgCost, Double ltp, Double outShare, String fund,
			Double fundShare, Double costValue, Double marketCost) {
		super();
		this.id = id;
		this.code = code;
		this.codeType = codeType;
		this.avgCost = avgCost;
		this.ltp = ltp;
		this.outShare = outShare;
		this.fund = fund;
		this.fundShare = fundShare;
		this.costValue = costValue;
		this.marketCost = marketCost;
	}

	public FundSummary(String code, String codeType, Double avgCost, Double ltp, Double outShare, String fund,
			Double fundShare, Double costValue, Double marketCost) {
		super();
		this.code = code;
		this.codeType = codeType;
		this.avgCost = avgCost;
		this.ltp = ltp;
		this.outShare = outShare;
		this.fund = fund;
		this.fundShare = fundShare;
		this.costValue = costValue;
		this.marketCost = marketCost;
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

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public Double getAvgCost() {
		return avgCost;
	}

	public void setAvgCost(Double avgCost) {
		this.avgCost = avgCost;
	}

	public Double getLtp() {
		return ltp;
	}

	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}

	public Double getOutShare() {
		return outShare;
	}

	public void setOutShare(Double outShare) {
		this.outShare = outShare;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public Double getFundShare() {
		return fundShare;
	}

	public void setFundShare(Double fundShare) {
		this.fundShare = fundShare;
	}

	public Double getCostValue() {
		return costValue;
	}

	public void setCostValue(Double costValue) {
		this.costValue = costValue;
	}

	public Double getMarketCost() {
		return marketCost;
	}

	public void setMarketCost(Double marketCost) {
		this.marketCost = marketCost;
	}

}
