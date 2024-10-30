package com.analyzer.automation.servive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.analyzer.automation.commonUtils.CommonUtils;

public class GetProductionData {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		getDividendData();
	}
	
	public static void getDividendData() throws ClassNotFoundException, SQLException {
		//Connection conLocal= CommonUtils.connectLocal();
		Connection conMain= CommonUtils.connectLocal();
		//String ticker=code;
		String select_query="select * from dse_analyzer.details_dividend_info";
		Statement s = conMain.createStatement();
	    ResultSet r = s.executeQuery(select_query);
		while(r.next()) {
			String ticker=r.getString(1);
			Double cash=r.getDouble(2);
			Double stock=r.getDouble(3);
			String date=r.getString(4);
			int year=r.getInt(5);
			
			String insert_query="insert into dse_analyzer_loader.dividend_details_archive (code, cash_div,stock_div,record_date,year) "
					+ "values ('"+ticker+"',"+cash+","+stock+",'"+date+"',"+year+")";
			Statement s1 = conMain.createStatement();
		    s1.executeUpdate(insert_query);	
		}
		//conLocal.close();
		conMain.close();	}
}
