package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.analyzer.automation.domain.DividendDetailsArchive;


@Repository
public interface DividendDetailsArchiveRepository extends JpaRepository<DividendDetailsArchive, Integer>{
	@Query(value="select * from ltm_sales_archive order by code",nativeQuery = true)
	ArrayList<DividendDetailsArchive> findAll();
	
	
	ArrayList<DividendDetailsArchive> findAllByCodeOrderByYearDesc(String code);
}
