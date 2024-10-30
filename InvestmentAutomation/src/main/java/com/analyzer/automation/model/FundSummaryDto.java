package com.analyzer.automation.model;

public class FundSummaryDto {
	
	private String code;
	private String fundType;
	private Double fundShare;
	private Double avgCost;
	private Double ltp;
	private Double costValue;
	private Double marketValue;
	private Double gainLossBDT;
	private Double gainLossRatio;
	private Double lR_Weight;
	private Double dSEX_Weight;
	private String lRtoDESX;
	public FundSummaryDto() {
		super();
	}
	public FundSummaryDto(String code, String fundType, Double fundShare, Double avgCost, Double ltp, Double costValue,
			Double marketValue, Double gainLossBDT, Double gainLossRatio, Double lR_Weight, Double dSEX_Weight,
			String lRtoDESX) {
		super();
		this.code = code;
		this.fundType = fundType;
		this.fundShare = fundShare;
		this.avgCost = avgCost;
		this.ltp = ltp;
		this.costValue = costValue;
		this.marketValue = marketValue;
		this.gainLossBDT = gainLossBDT;
		this.gainLossRatio = gainLossRatio;
		this.lR_Weight = lR_Weight;
		this.dSEX_Weight = dSEX_Weight;
		this.lRtoDESX = lRtoDESX;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFundtype() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public Double getFundShare() {
		return fundShare;
	}
	public void setFundShare(Double fundShare) {
		this.fundShare = fundShare;
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
	public Double getCostValue() {
		return costValue;
	}
	public void setCostValue(Double costValue) {
		this.costValue = costValue;
	}
	public Double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	public Double getGainLossBDT() {
		return gainLossBDT;
	}
	public void setGainLossBDT(Double gainLossBDT) {
		this.gainLossBDT = gainLossBDT;
	}
	public Double getGainLossRatio() {
		return gainLossRatio;
	}
	public void setGainLossRatio(Double gainLossRatio) {
		this.gainLossRatio = gainLossRatio;
	}
	public Double getlR_Weight() {
		return lR_Weight;
	}
	public void setlR_Weight(Double lR_Weight) {
		this.lR_Weight = lR_Weight;
	}
	public Double getdSEX_Weight() {
		return dSEX_Weight;
	}
	public void setdSEX_Weight(Double dSEX_Weight) {
		this.dSEX_Weight = dSEX_Weight;
	}
	public String getlRtoDESX() {
		return lRtoDESX;
	}
	public void setlRtoDESX(String lRtoDESX) {
		this.lRtoDESX = lRtoDESX;
	}
	
}
