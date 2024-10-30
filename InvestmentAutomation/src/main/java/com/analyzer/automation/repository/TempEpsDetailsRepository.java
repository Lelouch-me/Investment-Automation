package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analyzer.automation.domain.TempEpsDetails;

@Repository
public interface TempEpsDetailsRepository extends JpaRepository<TempEpsDetails, Integer>{
	@Query(value="select * from temp_eps_details order by code",nativeQuery = true)
	ArrayList<TempEpsDetails> findAll();
}
