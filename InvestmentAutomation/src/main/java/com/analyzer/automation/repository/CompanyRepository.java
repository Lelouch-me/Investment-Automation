package com.analyzer.automation.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analyzer.automation.domain.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Integer>{
	@Query(value="SELECT * FROM dse_analyzer.company where sector='Mutual Fund' and last_update_time_pb is not null;",nativeQuery = true)
	ArrayList<Company> findTickerMF();
	
	@Query(value="SELECT * FROM dse_analyzer.company where trading_status='trading';",nativeQuery = true)
	ArrayList<Company> findTicker();
	
	@Query(value="SELECT equities_per_share FROM dse_analyzer.company where code =?1 ;",nativeQuery = true)
	Double findByCodeequities_per_share(String code);
}
