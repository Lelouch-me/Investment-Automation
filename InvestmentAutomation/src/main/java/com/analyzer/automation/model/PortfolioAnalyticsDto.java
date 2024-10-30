package com.analyzer.automation.model;

public class PortfolioAnalyticsDto {
	private String code;
	private String codeType;
	private Double ltp;
	private Double eps;
	private Double bv;
	private Double pe;
	private Double pbv;
	private Double dividendYield;
	private Double week52HighValue;
	private Double week52LowValue;
	private Double week52HighRatio;
	private Double week52LowRatio;
	private Double costValue;
	private Double marketValue;
	private Double lR_Weight;
	private Double dSEX_Weight;
	private Double dSE30_Weight;
	private String lRtoDESX;
	private String lRtoDSE30;
	
	
	public PortfolioAnalyticsDto() {
		super();
	}


	public PortfolioAnalyticsDto(String code, String codeType, Double ltp, Double eps, Double bv, Double pe,
			Double pbv, Double dividendYield, Double week52HighValue, Double week52LowValue, Double week52HighRatio,
			Double week52LowRatio, Double costValue, Double marketValue, Double lR_Weight, Double dSEX_Weight,
			Double dSE30_Weight, String lRtoDESX, String lRtoDSE30) {
		super();
		this.code = code;
		this.codeType = codeType;
		this.ltp = ltp;
		this.eps = eps;
		this.bv = bv;
		this.pe = pe;
		this.pbv = pbv;
		this.dividendYield = dividendYield;
		this.week52HighValue = week52HighValue;
		this.week52LowValue = week52LowValue;
		this.week52HighRatio = week52HighRatio;
		this.week52LowRatio = week52LowRatio;
		this.costValue = costValue;
		this.marketValue = marketValue;
		this.lR_Weight = lR_Weight;
		this.dSEX_Weight = dSEX_Weight;
		this.dSE30_Weight = dSE30_Weight;
		this.lRtoDESX = lRtoDESX;
		this.lRtoDSE30 = lRtoDSE30;
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


	public Double getLtp() {
		return ltp;
	}


	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}


	public Double getEps() {
		return eps;
	}


	public void setEps(Double eps) {
		this.eps = eps;
	}


	public Double getBv() {
		return bv;
	}


	public void setBv(Double bv) {
		this.bv = bv;
	}


	public Double getPe() {
		return pe;
	}


	public void setPe(Double pe) {
		this.pe = pe;
	}


	public Double getPbv() {
		return pbv;
	}


	public void setPbv(Double pbv) {
		this.pbv = pbv;
	}


	public Double getDividendYield() {
		return dividendYield;
	}


	public void setDividendYield(Double dividendYield) {
		this.dividendYield = dividendYield;
	}


	public Double getWeek52HighValue() {
		return week52HighValue;
	}


	public void setWeek52HighValue(Double week52HighValue) {
		this.week52HighValue = week52HighValue;
	}


	public Double getWeek52LowValue() {
		return week52LowValue;
	}


	public void setWeek52LowValue(Double week52LowValue) {
		this.week52LowValue = week52LowValue;
	}


	public Double getWeek52HighRatio() {
		return week52HighRatio;
	}


	public void setWeek52HighRatio(Double week52HighRatio) {
		this.week52HighRatio = week52HighRatio;
	}


	public Double getWeek52LowRatio() {
		return week52LowRatio;
	}


	public void setWeek52LowRatio(Double week52LowRatio) {
		this.week52LowRatio = week52LowRatio;
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


	public Double getdSE30_Weight() {
		return dSE30_Weight;
	}


	public void setdSE30_Weight(Double dSE30_Weight) {
		this.dSE30_Weight = dSE30_Weight;
	}


	public String getlRtoDESX() {
		return lRtoDESX;
	}


	public void setlRtoDESX(String lRtoDESX) {
		this.lRtoDESX = lRtoDESX;
	}


	public String getlRtoDSE30() {
		return lRtoDSE30;
	}


	public void setlRtoDSE30(String lRtoDSE30) {
		this.lRtoDSE30 = lRtoDSE30;
	}
	
}
