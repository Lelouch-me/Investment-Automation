package com.analyzer.automation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analyzer.automation.domain.SectorWeight;

@Repository
public interface SectorWeightRepository extends JpaRepository<SectorWeight, Integer>{
	List<SectorWeight> findAll();
}
