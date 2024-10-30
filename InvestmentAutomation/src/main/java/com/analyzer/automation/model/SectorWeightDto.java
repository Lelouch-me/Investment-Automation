package com.analyzer.automation.model;

public class SectorWeightDto {
	
	private int id;
	private String sector;
	private Double ltmRevenue;	
	private Double ltmEps;
	private Double gpMargin;
	private Double opMargin;
	private Double npMargin;
	private Double sectorWeight;
	
	
	public SectorWeightDto() {
		super();
	}

	public SectorWeightDto(int id, String sector, Double ltmRevenue, Double ltmEps, Double gpMargin, Double opMargin,
			Double npMargin, Double sectorWeight) {
		super();
		this.id = id;
		this.sector = sector;
		this.ltmRevenue = ltmRevenue;
		this.ltmEps = ltmEps;
		this.gpMargin = gpMargin;
		this.opMargin = opMargin;
		this.npMargin = npMargin;
		this.sectorWeight = sectorWeight;
	}
	
	public SectorWeightDto(String sector, Double ltmRevenue, Double ltmEps, Double gpMargin, Double opMargin,
			Double npMargin, Double sectorWeight) {
		super();		
		this.sector = sector;
		this.ltmRevenue = ltmRevenue;
		this.ltmEps = ltmEps;
		this.gpMargin = gpMargin;
		this.opMargin = opMargin;
		this.npMargin = npMargin;
		this.sectorWeight = sectorWeight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Double getLtmRevenue() {
		return ltmRevenue;
	}

	public void setLtmRevenue(Double ltmRevenue) {
		this.ltmRevenue = ltmRevenue;
	}

	public Double getLtmEps() {
		return ltmEps;
	}

	public void setLtmEps(Double ltmEps) {
		this.ltmEps = ltmEps;
	}

	public Double getGpMargin() {
		return gpMargin;
	}

	public void setGpMargin(Double gpMargin) {
		this.gpMargin = gpMargin;
	}

	public Double getOpMargin() {
		return opMargin;
	}

	public void setOpMargin(Double opMargin) {
		this.opMargin = opMargin;
	}

	public Double getNpMargin() {
		return npMargin;
	}

	public void setNpMargin(Double npMargin) {
		this.npMargin = npMargin;
	}

	public Double getSectorWeight() {
		return sectorWeight;
	}

	public void setSectorWeight(Double sectorWeight) {
		this.sectorWeight = sectorWeight;
	}
}
