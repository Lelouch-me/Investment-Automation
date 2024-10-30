package com.analyzer.automation.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "user_action")
public class UserAction {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
		
	@Column(name = "username")
	private String username;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "type")
	private String type;
	
//	 @Temporal(TemporalType.TIMESTAMP)
//	 Date creationDateTime;
	
	@Column(name = "tickers")
	private String tickers;
	
	public UserAction() {
		super();
	}
	
	public UserAction(String username,String action,String type,String tickers) {
		this.username = username;
		this.action = action;
		this.type = type;
		this.tickers = tickers;
	}
}
