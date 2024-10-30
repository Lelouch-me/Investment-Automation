package com.analyzer.automation.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analyzer.automation.domain.FundSummary;


@Repository
public interface FundSummaryRepository extends JpaRepository<FundSummary, Integer>{
	List<FundSummary> findAllByCode(String code);
	
	@Query(value="SELECT distinct code FROM dse_analyzer_loader.fund_summary",nativeQuery = true)
	Set<String> findCode();
	
	@Query(value="SELECT * FROM dse_analyzer_loader.fund_summary where fund =?1",nativeQuery = true)
	List<FundSummary> findByFund(String fund);
	
	@Query(value="SELECT sum(last_mcap) FROM dse_analyzer.market_data",nativeQuery = true)
	Double findTotalMcap();
	
	@Query(value="SELECT  sum(last_mcap) FROM dse_analyzer.market_data where code in (SELECT code FROM dse_analyzer.daily_company_info where dse30='Y')",nativeQuery = true)
	Double findDSE30Mcap();
	
	@Query(value="SELECT last_mcap FROM dse_analyzer.market_data where code=?1",nativeQuery = true)
	Double findTickerMcap(String code);
	
	@Query(value="SELECT ltp FROM dse_analyzer.market_stat where code=?1",nativeQuery = true)
	Double findTickerLtp(String code);
	
	@Query(value="SELECT eps FROM dse_analyzer.company where code=?1",nativeQuery = true)
	Double findTickerEps(String code);
	
	@Query(value="SELECT equities_per_share FROM dse_analyzer.company where code=?1",nativeQuery = true)
	Double findTickerBv(String code);
	
	@Query(value="SELECT dvd_yield FROM dse_analyzer.market_data where code=?1",nativeQuery = true)
	Double findTickerDividendYield(String code);
	
	@Query(value="SELECT w52_price_range FROM dse_analyzer.daily_company_info where code=?1",nativeQuery = true)
	String findTickerWeek52(String code);
	
	@Query(value="SELECT sum(cost_value) FROM dse_analyzer_loader.fund_summary where code=?1",nativeQuery = true)
	Double findTickerCostValue(String code);
		
	@Query(value="SELECT sum(fund_share) FROM dse_analyzer_loader.fund_summary where code=?1",nativeQuery = true)
	Double findTotalHolding(String code);
	
	@Query(value="SELECT fund_share FROM dse_analyzer_loader.fund_summary where fund=?1 and code =?2",nativeQuery = true)
	Double findTickerHolding(String fund, String code);
	
	@Query(value="SELECT cash_dividend FROM dse_analyzer.dividend_info where code =?1 order by year desc limit 1",nativeQuery = true)
	Double findTickerDividend(String code);
	
	@Query(value="SELECT distinct fund FROM dse_analyzer_loader.fund_summary",nativeQuery = true)
	Set<String> findFund();
	
	@Query(value="SELECT * FROM dse_analyzer_loader.fund_summary where code =?1",nativeQuery = true)
	List<FundSummary> findByCode(String code);
	
	@Query(value="SELECT * FROM dse_analyzer_loader.fund_summary where code =?1 and fund =?2",nativeQuery = true)
	List<FundSummary> findByCodeAndFund(String code, String fund);
	
	@Query(value="SELECT ltp FROM dse_analyzer_loader.fund_summary where fund=?1 and code =?2",nativeQuery = true)
	Double findTickerFundLtp(String fund,String code);
}
