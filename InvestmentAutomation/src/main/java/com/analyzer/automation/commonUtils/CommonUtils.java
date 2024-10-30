package com.analyzer.automation.commonUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.analyzer.automation.model.CompanyDto;

public class CommonUtils {
		
	public static String[] getTickerArray(List<CompanyDto> mfTickerList) {
		String[] tickers = new String[mfTickerList.size()];
		int i = 0;
		for(CompanyDto marketStatDto : mfTickerList) {
			tickers[i] = marketStatDto.getCode();
			i++;
		}		
		return tickers;
	}
	public static Set<String> getLtpArray() {
		Set<String> funds = new HashSet<String>() ;
		funds.add("CAPMUF");
		funds.add("CIBBLSF");
		funds.add("CSRBGF");
		funds.add("SEBL1STMF");
		funds.add("MBLUF");
		funds.add("SAMLIUF");
		return funds;
	}
//	public static Connection connectLocal() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://119.148.4.4:3306/dse_analyzer_loader?rewriteBatchedStatements=true", "developer",
//		"developer4");
//		con.setAutoCommit(true);
//		return con;
//	}
	
	public static Connection connectLocal() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.100.12:3306/dse_analyzer_loader?rewriteBatchedStatements=true", "lrgb_loader",
		"developer4");
		con.setAutoCommit(true);
		return con;
	}
}
