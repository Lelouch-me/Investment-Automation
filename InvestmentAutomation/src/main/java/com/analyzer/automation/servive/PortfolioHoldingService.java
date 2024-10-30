package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.FundSummary;
import com.analyzer.automation.model.PortfolioAnalyticsDto;
import com.analyzer.automation.model.PortfolioHoldingsDto;
import com.analyzer.automation.repository.CompanyRepository;
import com.analyzer.automation.repository.FundSummaryRepository;

@Service
public class PortfolioHoldingService {
	private Double navAtMp=9217015590.91;
	private Double totalMcap;
	private Double tickerMcap;
	private Double NavDBHUnit = 120000000.00;
	private Double NavGDMFUnit = 150000000.00;
	private Double NavAIBLUnit = 100000000.00;
	private Double NavMBLUnit = 100000000.00;
	private Double NavLRGBUnit = 311080332.00;
	private Double NavNCCBLUnit = 108503401.00;
	private Double GDMFequities = 9.97;
	private Double LRGBequities = 10.51;
	
	@Autowired
	private FundSummaryRepository fundSummaryRepository;
	
	private CompanyRepository companyRepository;
	
	public PortfolioHoldingService(FundSummaryRepository fundSummaryRepository, CompanyRepository companyRepository) {
		super();
		this.fundSummaryRepository = fundSummaryRepository;
		this.companyRepository = companyRepository;
	}
	
	public List<PortfolioHoldingsDto> getPortfolioHolding(){
		List<PortfolioHoldingsDto> fundSummaryDtoList = new ArrayList<PortfolioHoldingsDto>();
		Set<String> fundSummaryy = fundSummaryRepository.findCode();
		if(fundSummaryy != null && !fundSummaryy.isEmpty()) {
			for(String fundSummary : fundSummaryy) {
				PortfolioHoldingsDto fundSummaryDto = convertDomainToDto(fundSummary);
				if(fundSummaryDto!=null) fundSummaryDtoList.add(fundSummaryDto);				
			}
		}
		return fundSummaryDtoList;	
	}
	
	private PortfolioHoldingsDto convertDomainToDto(String fundSummary) {
		
		String code=fundSummary;
		Double totalHolding=fundSummaryRepository.findTotalHolding(code) !=null ? fundSummaryRepository.findTotalHolding(code): null;
		Double dbhHolding=fundSummaryRepository.findTickerHolding("DBH1STMF", code);
		Double gdmfHolding=fundSummaryRepository.findTickerHolding("GREENDELMF", code);
		Double aiblHolding=fundSummaryRepository.findTickerHolding("AIBL1STIMF", code);
		Double mblHolding=fundSummaryRepository.findTickerHolding("MBL1STMF", code);
		Double lrgbHolding=fundSummaryRepository.findTickerHolding("LRGLOBMF1", code);
		Double nccblHolding=fundSummaryRepository.findTickerHolding("NCCBLMF1", code);
		Double costValue=fundSummaryRepository.findTickerCostValue(fundSummary);
		Double avgCost=totalHolding !=null ? costValue/totalHolding :null;
		Double marketPrice=fundSummaryRepository.findTickerLtp(fundSummary) !=null ? fundSummaryRepository.findTickerLtp(fundSummary): 0;
		Double marketValue=totalHolding !=null ?  marketPrice*totalHolding :  null;
		Double gainLoss= marketValue !=null ?marketValue-costValue : null;
		Double gainLossRatio=marketValue !=null ?(marketValue-costValue)-1: null;
		Double cashReturnRatio= fundSummaryRepository.findTickerDividend(code)!=null&& avgCost !=null ?(fundSummaryRepository.findTickerDividend(code)/avgCost)*10 : null;
		Double totalReturnRatio=cashReturnRatio !=null ? cashReturnRatio+gainLossRatio : null;
		Double priceWeightRatio=marketValue !=null ? marketValue/navAtMp: null;
		
		totalMcap=fundSummaryRepository.findTotalMcap();
		tickerMcap=fundSummaryRepository.findTickerMcap(fundSummary);
		Double marketWeightRatio=tickerMcap!=null ? tickerMcap/totalMcap: 0.0;
		Double overUnderWeightRatio=priceWeightRatio !=null ?priceWeightRatio-marketWeightRatio : null;
		Double baseValue=null;
		Double bestValue=null;
		Double worstValue=null;
		
		
 
		return new PortfolioHoldingsDto(code,totalHolding,dbhHolding,gdmfHolding,aiblHolding,mblHolding,lrgbHolding,nccblHolding,avgCost,marketPrice,costValue,
				marketValue,gainLoss,gainLossRatio,cashReturnRatio,totalReturnRatio,priceWeightRatio,marketWeightRatio,overUnderWeightRatio,baseValue,
				bestValue,worstValue);
	}
	
	public List<PortfolioHoldingsDto> getWeightedHolding(){
		List<PortfolioHoldingsDto> fundSummaryDtoList = new ArrayList<PortfolioHoldingsDto>();
		Set<String> fundSummaryy = fundSummaryRepository.findCode();
		if(fundSummaryy != null && !fundSummaryy.isEmpty()) {
			for(String fundSummary : fundSummaryy) {
				PortfolioHoldingsDto fundSummaryDto = weightedconvertDomainToDto(fundSummary);
				if(fundSummaryDto!=null) fundSummaryDtoList.add(fundSummaryDto);				
			}
		}
		return fundSummaryDtoList;	
	}
	
	private PortfolioHoldingsDto weightedconvertDomainToDto(String fundSummary) {
		
		String code=fundSummary;
		
		Double dbhTotalNav = companyRepository.findByCodeequities_per_share("DBH1STMF") * NavDBHUnit;
		Double gdmfTotalNav = GDMFequities * NavGDMFUnit;
		Double aiblTotalNav = companyRepository.findByCodeequities_per_share("AIBL1STIMF") * NavAIBLUnit;
		Double mblTotalNav = companyRepository.findByCodeequities_per_share("MBL1STMF") * NavMBLUnit;
		Double lrgbTotalNav = LRGBequities * NavLRGBUnit;
		Double nccblTotalNav = companyRepository.findByCodeequities_per_share("NCCBLMF1") * NavNCCBLUnit;
		
		
		Double totalHolding=fundSummaryRepository.findTotalHolding(code) !=null ? fundSummaryRepository.findTotalHolding(code): null;
		Double dbhHolding=(((fundSummaryRepository.findTickerHolding("DBH1STMF", code) != null ? fundSummaryRepository.findTickerHolding("DBH1STMF", code) : 0.00) * fundSummaryRepository.findTickerFundLtp("DBH1STMF",code))/dbhTotalNav)*100.00;
		Double gdmfHolding=(((fundSummaryRepository.findTickerHolding("GREENDELMF", code) != null ? fundSummaryRepository.findTickerHolding("GREENDELMF", code) : 0.00) * fundSummaryRepository.findTickerFundLtp("GREENDELMF",code))/gdmfTotalNav)*100.00;
		Double aiblHolding=(((fundSummaryRepository.findTickerHolding("AIBL1STIMF", code) != null ? fundSummaryRepository.findTickerHolding("AIBL1STIMF", code) : 0.00) * fundSummaryRepository.findTickerFundLtp("AIBL1STIMF",code))/aiblTotalNav)*100.00;
		Double mblHolding=(((fundSummaryRepository.findTickerHolding("MBL1STMF", code) != null ? fundSummaryRepository.findTickerHolding("MBL1STMF", code) : 0.00) * fundSummaryRepository.findTickerFundLtp("MBL1STMF",code))/mblTotalNav)*100.00;
		Double lrgbHolding=(((fundSummaryRepository.findTickerHolding("LRGLOBMF1", code) != null ? fundSummaryRepository.findTickerHolding("LRGLOBMF1", code) : 0.00) * fundSummaryRepository.findTickerFundLtp("LRGLOBMF1",code))/lrgbTotalNav)*100.00;
		Double nccblHolding=(((fundSummaryRepository.findTickerHolding("NCCBLMF1", code) != null ? fundSummaryRepository.findTickerHolding("NCCBLMF1", code) : 0.00) * fundSummaryRepository.findTickerFundLtp("NCCBLMF1",code))/nccblTotalNav)*100.00;
		Double costValue=fundSummaryRepository.findTickerCostValue(fundSummary);
		Double avgCost=totalHolding !=null ? costValue/totalHolding :null;
		Double marketPrice=fundSummaryRepository.findTickerLtp(fundSummary) !=null ? fundSummaryRepository.findTickerLtp(fundSummary): 0;
		Double marketValue=totalHolding !=null ?  marketPrice*totalHolding :  null;
		Double gainLoss= marketValue !=null ?marketValue-costValue : null;
		Double gainLossRatio=marketValue !=null ?(marketValue-costValue)-1: null;
		Double cashReturnRatio= fundSummaryRepository.findTickerDividend(code)!=null ?(fundSummaryRepository.findTickerDividend(code)/avgCost)*10 : null;
		Double totalReturnRatio=cashReturnRatio !=null ? cashReturnRatio+gainLossRatio : null;
		Double priceWeightRatio=marketValue !=null ? marketValue/navAtMp: null;
		
		totalMcap=fundSummaryRepository.findTotalMcap();
		tickerMcap=fundSummaryRepository.findTickerMcap(fundSummary);
		Double marketWeightRatio=tickerMcap!=null ? tickerMcap/totalMcap: 0.0;
		Double overUnderWeightRatio=priceWeightRatio !=null ?priceWeightRatio-marketWeightRatio : null;
		Double baseValue=null;
		Double bestValue=null;
		Double worstValue=null;
		
		
 
		return new PortfolioHoldingsDto(code,totalHolding,dbhHolding,gdmfHolding,aiblHolding,mblHolding,lrgbHolding,nccblHolding,avgCost,marketPrice,costValue,
				marketValue,gainLoss,gainLossRatio,cashReturnRatio,totalReturnRatio,priceWeightRatio,marketWeightRatio,overUnderWeightRatio,baseValue,
				bestValue,worstValue);
	}

}
