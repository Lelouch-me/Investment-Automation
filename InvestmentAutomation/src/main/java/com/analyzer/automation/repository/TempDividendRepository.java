package com.analyzer.automation.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.analyzer.automation.domain.TempDividend;


@Repository
public interface TempDividendRepository extends JpaRepository<TempDividend, Integer>{
	@Query(value="select * from temp_dividend order by date",nativeQuery = true)
	ArrayList<TempDividend> findAll();
}
