package com.analyzer.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analyzer.automation.domain.UserAction;

public interface UserActionRepository extends JpaRepository<UserAction, Integer>{

}
