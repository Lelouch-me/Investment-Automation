package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.Company;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.repository.CompanyRepository;


@Service
public class CompanyService {

private CompanyRepository companytRepository;
	
	public CompanyService(CompanyRepository companytRepository) {
		super();
		this.companytRepository = companytRepository;
	}
	
	public List<CompanyDto> getTickerMF(){
		List<CompanyDto> reportDtoList = new ArrayList<CompanyDto>();
		List<Company> companyDtls = companytRepository.findTickerMF();
		if(companyDtls != null && !companyDtls.isEmpty()) {
			for(Company company : companyDtls) {
				CompanyDto companyDto = convertDomainToDto(company);
				if(companyDto!=null) reportDtoList.add(companyDto);				
			}
		}
		return reportDtoList;	
	}
	
	public List<CompanyDto> getTicker(){
		List<CompanyDto> reportDtoList = new ArrayList<CompanyDto>();
		List<Company> companyDtls = companytRepository.findTicker();
		if(companyDtls != null && !companyDtls.isEmpty()) {
			for(Company company : companyDtls) {
				CompanyDto companyDto = convertDomainToDto(company);
				if(companyDto!=null) reportDtoList.add(companyDto);				
			}
		}
		return reportDtoList;	
	}

	private CompanyDto convertDomainToDto(Company company) {
		return new CompanyDto(company.getCode(), company.getCompanyName());
	}


}
