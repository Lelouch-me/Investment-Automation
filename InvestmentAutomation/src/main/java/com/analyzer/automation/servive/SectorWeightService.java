package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.SectorWeight;
import com.analyzer.automation.model.SectorWeightDto;
import com.analyzer.automation.repository.SectorWeightRepository;

@Service
public class SectorWeightService {
	
private SectorWeightRepository sectorWeightRepository;
	
	public SectorWeightService(SectorWeightRepository sectorWeightRepository) {
		super();
		this.sectorWeightRepository = sectorWeightRepository;
	}
	
	public List<SectorWeightDto> getSectorWeight(){
		List<SectorWeightDto> sectorWeightDtoList = new ArrayList<SectorWeightDto>();
		List<SectorWeight> ssectorWeight = sectorWeightRepository.findAll();
		if(ssectorWeight != null && !ssectorWeight.isEmpty()) {
			for(SectorWeight sectorWeight : ssectorWeight) {
				SectorWeightDto sectorWeightDto = convertDomainToDto(sectorWeight);
				if(sectorWeightDto!=null) sectorWeightDtoList.add(sectorWeightDto);				
			}
		}
		return sectorWeightDtoList;	
	}
	
	private SectorWeightDto convertDomainToDto(SectorWeight sectorWeight) {
		return new SectorWeightDto(sectorWeight.getId(),sectorWeight.getSector(),sectorWeight.getLtmRevenue(),
				sectorWeight.getLtmEps(),sectorWeight.getGpMargin(),sectorWeight.getOpMargin(),sectorWeight.getNpMargin(),sectorWeight.getSectorWeight());
	}

}
