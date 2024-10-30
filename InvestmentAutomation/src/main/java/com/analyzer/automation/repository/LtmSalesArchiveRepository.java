package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.analyzer.automation.domain.LtmSalesArchive;


@Repository
public interface LtmSalesArchiveRepository extends JpaRepository<LtmSalesArchive, Integer>{
	@Query(value="select * from ltm_sales_archive order by code",nativeQuery = true)
	ArrayList<LtmSalesArchive> findAll();
	
	
	ArrayList<LtmSalesArchive> findAllByCodeOrderByYearDesc(String code);
}
