package com.analyzer.automation.model;

public class PortfolioHoldingsDto {
	private String code;
	private Double totalHolding;
	private Double dbhHolding;
	private Double gdmfHolding;
	private Double aiblHolding;
	private Double mblHolding;
	private Double lrgbHolding;
	private Double nccblHolding;
	private Double avgCost;
	private Double marketPrice;
	private Double costValue;
	private Double marketValue;
	private Double gainLoss;
	private Double gainLossRatio;
	private Double cashReturnRatio;
	private Double totalReturnRatio;
	private Double priceWeightRatio;
	private Double marketWeightRatio;
	private Double overUnderWeightRatio;
	private Double baseValue;
	private Double bestValue;
	private Double worstValue;
	public PortfolioHoldingsDto() {
		super();
	}
	public PortfolioHoldingsDto(String code, Double totalHolding, Double dbhHolding, Double gdmfHolding,
			Double aiblHolding, Double mblHolding, Double lrgbHolding, Double nccblHolding, Double avgCost,
			Double marketPrice, Double costValue, Double marketValue, Double gainLoss, Double gainLossRatio,
			Double cashReturnRatio, Double totalReturnRatio, Double priceWeightRatio, Double marketWeightRatio,
			Double overUnderWeightRatio, Double baseValue, Double bestValue, Double worstValue) {
		super();
		this.code = code;
		this.totalHolding = totalHolding;
		this.dbhHolding = dbhHolding;
		this.gdmfHolding = gdmfHolding;
		this.aiblHolding = aiblHolding;
		this.mblHolding = mblHolding;
		this.lrgbHolding = lrgbHolding;
		this.nccblHolding = nccblHolding;
		this.avgCost = avgCost;
		this.marketPrice = marketPrice;
		this.costValue = costValue;
		this.marketValue = marketValue;
		this.gainLoss = gainLoss;
		this.gainLossRatio = gainLossRatio;
		this.cashReturnRatio = cashReturnRatio;
		this.totalReturnRatio = totalReturnRatio;
		this.priceWeightRatio = priceWeightRatio;
		this.marketWeightRatio = marketWeightRatio;
		this.overUnderWeightRatio = overUnderWeightRatio;
		this.baseValue = baseValue;
		this.bestValue = bestValue;
		this.worstValue = worstValue;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getTotalHolding() {
		return totalHolding;
	}
	public void setTotalHolding(Double totalHolding) {
		this.totalHolding = totalHolding;
	}
	public Double getDbhHolding() {
		return dbhHolding;
	}
	public void setDbhHolding(Double dbhHolding) {
		this.dbhHolding = dbhHolding;
	}
	public Double getGdmfHolding() {
		return gdmfHolding;
	}
	public void setGdmfHolding(Double gdmfHolding) {
		this.gdmfHolding = gdmfHolding;
	}
	public Double getAiblHolding() {
		return aiblHolding;
	}
	public void setAiblHolding(Double aiblHolding) {
		this.aiblHolding = aiblHolding;
	}
	public Double getMblHolding() {
		return mblHolding;
	}
	public void setMblHolding(Double mblHolding) {
		this.mblHolding = mblHolding;
	}
	public Double getLrgbHolding() {
		return lrgbHolding;
	}
	public void setLrgbHolding(Double lrgbHolding) {
		this.lrgbHolding = lrgbHolding;
	}
	public Double getNccblHolding() {
		return nccblHolding;
	}
	public void setNccblHolding(Double nccblHolding) {
		this.nccblHolding = nccblHolding;
	}
	public Double getAvgCost() {
		return avgCost;
	}
	public void setAvgCost(Double avgCost) {
		this.avgCost = avgCost;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
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
	public Double getGainLoss() {
		return gainLoss;
	}
	public void setGainLoss(Double gainLoss) {
		this.gainLoss = gainLoss;
	}
	public Double getGainLossRatio() {
		return gainLossRatio;
	}
	public void setGainLossRatio(Double gainLossRatio) {
		this.gainLossRatio = gainLossRatio;
	}
	public Double getCashReturnRatio() {
		return cashReturnRatio;
	}
	public void setCashReturnRatio(Double cashReturnRatio) {
		this.cashReturnRatio = cashReturnRatio;
	}
	public Double getTotalReturnRatio() {
		return totalReturnRatio;
	}
	public void setTotalReturnRatio(Double totalReturnRatio) {
		this.totalReturnRatio = totalReturnRatio;
	}
	public Double getPriceWeightRatio() {
		return priceWeightRatio;
	}
	public void setPriceWeightRatio(Double priceWeightRatio) {
		this.priceWeightRatio = priceWeightRatio;
	}
	public Double getMarketWeightRatio() {
		return marketWeightRatio;
	}
	public void setMarketWeightRatio(Double marketWeightRatio) {
		this.marketWeightRatio = marketWeightRatio;
	}
	public Double getOverUnderWeightRatio() {
		return overUnderWeightRatio;
	}
	public void setOverUnderWeightRatio(Double overUnderWeightRatio) {
		this.overUnderWeightRatio = overUnderWeightRatio;
	}
	public Double getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Double baseValue) {
		this.baseValue = baseValue;
	}
	public Double getBestValue() {
		return bestValue;
	}
	public void setBestValue(Double bestValue) {
		this.bestValue = bestValue;
	}
	public Double getWorstValue() {
		return worstValue;
	}
	public void setWorstValue(Double worstValue) {
		this.worstValue = worstValue;
	}	

}
