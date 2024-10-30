package com.analyzer.automation.servive;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.commonUtils.CommonUtils;
import com.analyzer.automation.domain.Company;
import com.analyzer.automation.domain.FundSummary;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.model.FundSummaryDto;
import com.analyzer.automation.repository.FundSummaryRepository;

@Service
public class FundSummaryService {
	private Set<String> tickerList = new HashSet<String>();
	
	@Autowired
	private FundSummaryRepository fundSummaryRepository;
	
	private Double totalNAV=0.0;
	private Double totalMcap;
	private Double tickerMcap;
	DecimalFormat df = new DecimalFormat("#.##");
	
	
	public FundSummaryService(FundSummaryRepository fundSummaryRepository) {
		super();
		this.fundSummaryRepository = fundSummaryRepository;
	}
	
	public List<FundSummaryDto> getFundSummary(String fund) throws ClassNotFoundException, SQLException{
		tickerList = CommonUtils.getLtpArray();
		List<FundSummaryDto> fundSummaryDtoList = new ArrayList<FundSummaryDto>();
		List<FundSummary> fundSummaryy = fundSummaryRepository.findByFund(fund);
		if(fundSummaryy != null && !fundSummaryy.isEmpty()) {
			for(FundSummary fundSummary : fundSummaryy) {
				FundSummaryDto fundSummaryDto = convertDomainToDto(fundSummary);
				if(fundSummaryDto!=null) fundSummaryDtoList.add(fundSummaryDto);				
			}
		}
		return fundSummaryDtoList;	
	}
	
	public List<FundSummaryDto> getCodeSummary(String code) throws ClassNotFoundException, SQLException{
		tickerList = CommonUtils.getLtpArray();
		List<FundSummaryDto> fundSummaryDtoList = new ArrayList<FundSummaryDto>();
		List<FundSummary> fundSummaryy = fundSummaryRepository.findByCode(code);
		if(fundSummaryy != null && !fundSummaryy.isEmpty()) {
			for(FundSummary fundSummary : fundSummaryy) {
				FundSummaryDto fundSummaryDto = convertDomainToDto(fundSummary);
				if(fundSummaryDto!=null) fundSummaryDtoList.add(fundSummaryDto);				
			}
		}
		return fundSummaryDtoList;	
	}
	
	private FundSummaryDto convertDomainToDto(FundSummary fundSummary) throws ClassNotFoundException, SQLException {		
		String code=fundSummary.getCode();
		
		Double ltp = 0.0;
		if(tickerList.contains(fundSummary.getCode()) || ltp == 0.0) {
			ltp=fundSummary.getLtp();
		} else {
			ltp=fundSummaryRepository.findTickerLtp(fundSummary.getCode());
		}
		Double fundShare = fundSummary.getFundShare() != null ? fundSummary.getFundShare() : 0.0;
		
		Double marketCost=ltp !=null && fundShare!= null ? fundShare*ltp : 0;
		Double gainLossBDT = marketCost-fundSummary.getCostValue();
		Double gainLossRatio = (marketCost/fundSummary.getCostValue())-1;
		getTotalNav(fundSummary.getFund());
		Double lR_Weight = (marketCost/totalNAV)*100;
		
		totalMcap=fundSummaryRepository.findTotalMcap();
		tickerMcap=fundSummaryRepository.findTickerMcap(fundSummary.getCode());
		
		Double dSEX_Weight =tickerMcap!=null ? (tickerMcap/totalMcap)*100: 0.0;
		
		String lRtoDESX = dSEX_Weight== 0.0 ? "OverWeight" :(lR_Weight>dSEX_Weight? "OverWeight": "UnderWeight"); 
		return new FundSummaryDto(fundSummary.getCode(),fundSummary.getFund(),fundShare,fundSummary.getAvgCost(),ltp,
				fundSummary.getCostValue(),marketCost,gainLossBDT,gainLossRatio,lR_Weight,dSEX_Weight,lRtoDESX);
	}
	
	public Set<String> getFund(){
		Set<String> fundList = fundSummaryRepository.findCode();
		return fundList;	
	}
	

	public List<FundSummaryDto> getCodeSummary(String code, String fundType) throws ClassNotFoundException, SQLException{
		List<FundSummaryDto> fundSummaryDtoList = new ArrayList<FundSummaryDto>();
		List<FundSummary> fundSummaryy = fundSummaryRepository.findByCodeAndFund(code,fundType);
		if(fundSummaryy != null && !fundSummaryy.isEmpty()) {
			for(FundSummary fundSummary : fundSummaryy) {
				FundSummaryDto fundSummaryDto = convertDomainToDto(fundSummary);
				if(fundSummaryDto!=null) fundSummaryDtoList.add(fundSummaryDto);				
			}
		}
		return fundSummaryDtoList;	
	}
	
	public void updateFundSummary(String code, String fundType, String costValue, String fundShare) throws SQLException, ClassNotFoundException {
		Double actualCostValue = Double.parseDouble(costValue.replace(",", ""));
		Double actualfundShare = Double.parseDouble(fundShare.replace(",", ""));
		Double avgCost=actualCostValue/actualfundShare;
		Connection con = CommonUtils.connectLocal();
		Statement stmt = con.createStatement();
		String query = "UPDATE dse_analyzer_loader.fund_summary SET avg_cost = "+avgCost+", cost_value = "+actualCostValue+", fund_share = "+actualfundShare+" WHERE code = '"+code+"' AND fund = '"+fundType+"';"; 
		stmt.execute(query);
		
//		fundSummaryRepository.updateFund(code, fundType, costValue, fundShare);
	}
	
	public void updateLtp(String code, String ltp) throws SQLException, ClassNotFoundException {
		Connection con = CommonUtils.connectLocal();
		Statement stmt = con.createStatement();
		String query = "UPDATE dse_analyzer_loader.fund_summary SET ltp = "+ltp+" WHERE code = '"+code+"';"; 
		stmt.execute(query);
		
//		fundSummaryRepository.updateFund(code, fundType, costValue, fundShare);
	}

	public void getTotalNav(String fund) throws ClassNotFoundException, SQLException {
		double equitiesPerShare = getEquitiesPerShare(fund);
		HashMap<String, Double> myMap = new HashMap<String, Double>() {{
            put("DBH1STMF", (double) 120000000);
            put("GREENDELMF", (double) 150000000);
            put("AIBL1STIMF", (double) 100000000);
            put("MBL1STMF", (double) 100000000);
            put("LRGLOBMF1", (double) 311080332);
            put("NCCBLMF1", (double)  108503401);
        }};
        Double unitNo=myMap.get(fund);
		totalNAV=unitNo*equitiesPerShare;
		
	}
	
	public Double getEquitiesPerShare(String fund) throws ClassNotFoundException, SQLException {
		Connection con = CommonUtils.connectLocal();
		Statement stmt = con.createStatement();
		Double value = 0.0;
		String query = "SELECT equities_per_share FROM dse_analyzer.company where code = '"+fund+"';";
		ResultSet rs = null;
		rs = stmt.executeQuery(query);
		while(rs.next()) {
			Double equitiesPerShare = rs.getDouble("equities_per_share");
			value = equitiesPerShare;
		}
		
		return value;

		
		
	}
}
