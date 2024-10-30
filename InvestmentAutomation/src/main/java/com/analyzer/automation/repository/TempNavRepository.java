package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analyzer.automation.domain.TempNav;

@Repository
public interface TempNavRepository extends JpaRepository<TempNav, Integer>{
	
	@Query(value="select * from temp_nav order by code",nativeQuery = true)
	ArrayList<TempNav> findAll();

}