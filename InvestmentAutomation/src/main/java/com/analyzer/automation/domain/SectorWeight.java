package com.analyzer.automation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sector_weight")
public class SectorWeight {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "sector")
	private String sector;
	
	@Column(name = "ltm_revenue")
	private Double ltmRevenue;
	
	@Column(name = "ltm_eps")
	private Double ltmEps;
	
	@Column(name = "gp_margin")
	private Double gpMargin;
	
	@Column(name = "op_margin")
	private Double opMargin;
	
	@Column(name = "np_margin")
	private Double npMargin;
	
	@Column(name = "sector_weight")
	private Double sectorWeight;

	public SectorWeight() {
		super();
	}

	public SectorWeight(int id, String sector, Double ltmRevenue, Double ltmEps, Double gpMargin, Double opMargin,
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
	
	public SectorWeight(String sector, Double ltmRevenue, Double ltmEps, Double gpMargin, Double opMargin,
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
