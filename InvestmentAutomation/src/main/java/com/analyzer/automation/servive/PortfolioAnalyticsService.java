package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.FundSummary;
import com.analyzer.automation.model.PortfolioAnalyticsDto;
import com.analyzer.automation.repository.FundSummaryRepository;

@Service
public class PortfolioAnalyticsService {
	private Double navAtMp=9217015590.91;
	private Double totalMcap;
	private Double tickerMcap;
	private Double dSE30Mcap;
	
	@Autowired
	private FundSummaryRepository fundSummaryRepository;
	
	public PortfolioAnalyticsService(FundSummaryRepository fundSummaryRepository) {
		super();
		this.fundSummaryRepository = fundSummaryRepository;
	}
	
	public List<PortfolioAnalyticsDto> getPortfolioAnalytics(){
		List<PortfolioAnalyticsDto> fundSummaryDtoList = new ArrayList<PortfolioAnalyticsDto>();
		List<FundSummary> fundSummaryy = fundSummaryRepository.findAll();
		if(fundSummaryy != null && !fundSummaryy.isEmpty()) {
			int j=1;
			for(FundSummary fundSummary : fundSummaryy) {
				PortfolioAnalyticsDto fundSummaryDto = convertDomainToDto(fundSummary);
				if(fundSummaryDto!=null) fundSummaryDtoList.add(fundSummaryDto);
				j++;
				if (j>49) {
					break;
				}
			}
		}
		return fundSummaryDtoList;	
	}
	
	private PortfolioAnalyticsDto convertDomainToDto(FundSummary fundSummary) {
		Double ltp = fundSummaryRepository.findTickerLtp(fundSummary.getCode()) !=null ? fundSummaryRepository.findTickerLtp(fundSummary.getCode()): 0;
		Double eps = fundSummaryRepository.findTickerEps(fundSummary.getCode()) !=null ? fundSummaryRepository.findTickerEps(fundSummary.getCode()): 0;
		Double bv = fundSummaryRepository.findTickerBv(fundSummary.getCode()) !=null ? fundSummaryRepository.findTickerBv(fundSummary.getCode()): 0;
		Double pe = ltp/eps;
		Double pbv = ltp/bv;
		Double dividendYield = fundSummaryRepository.findTickerDividendYield(fundSummary.getCode()) !=null ?
								fundSummaryRepository.findTickerDividendYield(fundSummary.getCode())*100 : 0;
		String week52=fundSummaryRepository.findTickerWeek52(fundSummary.getCode()) !=null ? fundSummaryRepository.findTickerWeek52(fundSummary.getCode()) : null;		
		Double week52HighValue =week52 !=null ? Double.parseDouble((week52.split(" - ") [1]).replaceAll("[^a-zA-Z0-9-.]", "")) : null;
		Double week52LowValue = week52 !=null ? Double.parseDouble((week52.split(" - ") [0]).replaceAll("[^a-zA-Z0-9-.]", "")) : null;
		Double week52HighRatio =week52HighValue !=null  ? ltp/week52HighValue : null;
		Double week52LowRatio = week52LowValue !=null ? ltp/week52LowValue : null;
		Double costValue = fundSummaryRepository.findTickerCostValue(fundSummary.getCode());
		Double marketValue = fundSummaryRepository.findTotalHolding(fundSummary.getCode()) !=null ? fundSummaryRepository.findTotalHolding(fundSummary.getCode())*ltp : null;
		Double lR_Weight = marketValue !=null ? marketValue/navAtMp : null;
		
		totalMcap=fundSummaryRepository.findTotalMcap();
		tickerMcap=fundSummaryRepository.findTickerMcap(fundSummary.getCode());
		
		dSE30Mcap=fundSummaryRepository.findDSE30Mcap();
		Double dSEX_Weight = tickerMcap!=null ? tickerMcap/totalMcap: 0.0;
		Double dSE30_Weight = tickerMcap!=null ? tickerMcap/dSE30Mcap: 0.0;
		String lRtoDESX = lR_Weight !=null ? lR_Weight>dSEX_Weight? "OverWeight": "UnderWeight" : null;
		String lRtoDSE30 = lR_Weight !=null ? lR_Weight>dSE30_Weight? "OverWeight": "UnderWeight" :null;
 
		return new PortfolioAnalyticsDto(fundSummary.getCode(),fundSummary.getCodeType(),ltp,eps,bv,pe,pbv,dividendYield,week52HighValue,week52LowValue,week52HighRatio,
				week52LowRatio,costValue,marketValue,lR_Weight,dSEX_Weight,dSE30_Weight,lRtoDESX,lRtoDSE30);
	}

}
