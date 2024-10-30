package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.analyzer.automation.domain.EpsDetailsArchive;



@Repository
public interface EpsDetailsArchiveRepository extends JpaRepository<EpsDetailsArchive, Integer>{
	@Query(value="select * from ltm_sales_archive order by code",nativeQuery = true)
	ArrayList<EpsDetailsArchive> findAll();
	
	
	ArrayList<EpsDetailsArchive> findAllByCodeOrderByYearDesc(String code);
}
