package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.analyzer.automation.domain.TempWeeklyNav;

@Repository
public interface TempWeeklyNavRepository extends JpaRepository<TempWeeklyNav, Integer> {
	
	@Query(value="select * from temp_weekly_nav order by code",nativeQuery = true)
	ArrayList<TempWeeklyNav> findAll();

}
