package com.analyzer.automation.servive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import com.analyzer.automation.commonUtils.CommonUtils;

public class UpdateProductionData {
	
	public static void populateAdjustedPrice(String code, Double stockDiv, Double cashDiv, String recordDate, int year) throws ClassNotFoundException, SQLException {
		String ticker = code;
		Connection con = CommonUtils.connectLocal();		
		try {											
			PreparedStatement pst = null;
			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01");					
				try {
					String dateCell = recordDate;	
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateCell);
					if(date.compareTo(startDate)>0) {						
						System.out.println(ticker);
						Double stockDivValue = stockDiv;
						Double cashDivValue = cashDiv;
						String dateValue = new SimpleDateFormat("yyyy-MM-dd").format(date);		
						int yearValue = year;
																			
						String insert_query = "insert into dse_analyzer.details_dividend_info values(?,?,?,?,?)";
						pst=con.prepareStatement(insert_query);
							
						pst.setString(1,ticker);
							
						if(stockDivValue!=null) {
							pst.setDouble(3,stockDivValue);
						}else {
							pst.setNull(3,java.sql.Types.NULL);
						}
						if(cashDivValue!=null) {
							pst.setDouble(2,cashDivValue);
						}else {
							pst.setNull(2,java.sql.Types.NULL);
						}
							pst.setString(4,dateValue);
							
						if(yearValue!=0) {
							pst.setInt(5,yearValue);
						}else {
							pst.setNull(5,java.sql.Types.NULL);
						}
						System.out.println(pst);
						pst.execute();						
						populateAdjustedPrice(ticker,con);
						populateVolume(ticker,con);
						System.out.println(ticker);
						con.close();
					}																		
			}catch(Exception e) {
					System.out.println(e);
				}						
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void populateAdjustedPrice(String  tic, Connection con) throws SQLException {
		String ticker=tic; 	    	
  	    boolean status = true;
  	    String lastDivDate = null;
  	    int i = 0;
  	    Map<String,Double> adjustedPriceMap = new LinkedHashMap<String,Double>();
  	    String div_query = "select * from dse_analyzer.details_dividend_info where code = '"+ticker+"' order by date desc";
  	    Statement st = con.createStatement();
  	    	ResultSet rs = st.executeQuery(div_query);
  	    	while(rs.next()) {
  	    		status = false;
  	    		String divDate = rs.getString(4);
  	    		Double cashDiv = rs.getDouble(2);
  	    		Double stockDiv = rs.getDouble(3);
  	    			
  	    		if(i==0) {
  	    			lastDivDate = divDate;
  	    		}
  	    		i++;
  	    			
  	    		String price_query = "select close,date from dse.daily_price where ticker = '"+ticker
  	    					+"' and date<='"+divDate+"' and date>='2015-07-25' and close!=0 order by date desc";
  	    		Statement priceStatement = con.createStatement();
  	  	    	ResultSet priceResultSet = priceStatement.executeQuery(price_query);
  	  	    	while(priceResultSet.next()) {
  	  	    		String date = priceResultSet.getString(2);
  	  	    		if(adjustedPriceMap.containsKey(date)) {
  	  	    			Double adjustedPrice = cashDiv!=null && cashDiv < adjustedPriceMap.get(date) ? 
  	  	    					(adjustedPriceMap.get(date) - cashDiv) : adjustedPriceMap.get(date);
  	  	    			if(stockDiv!=null) {
  	  	    				adjustedPrice = adjustedPrice/(1+stockDiv);
  	  	    			}
  	  	    			adjustedPriceMap.put(date,adjustedPrice);
  	  	    			}else {
  	  	    				Double adjustedPrice = cashDiv!=null && cashDiv < priceResultSet.getDouble(1) ? 
  	  	    					(priceResultSet.getDouble(1) - cashDiv) : priceResultSet.getDouble(1);
  	  	    			if(stockDiv!=null) {
  	  	    				adjustedPrice = adjustedPrice/(1+stockDiv);
  	  	    			}
  	  	    				adjustedPriceMap.put(date,adjustedPrice);
  	  	    			}
  	  	    		}
  	    		}
  	    		
  	    	String insert_query = "insert ignore into dse_analyzer.adjusted_price(code,adjstd_close,date) values(?,?,?)";
	  	    PreparedStatement pst=con.prepareStatement(insert_query);
	  	    	
  	    	if(status) {
  	    		String q = "select close,date from dse.daily_price where ticker='"+ticker+"' and date>='2015-07-25' and close!=0 order by date";
  	    		Statement s = con.createStatement();
  	    		ResultSet r = s.executeQuery(q);
  	    		while(r.next()) {
  	    			pst.setString(1,ticker);
  	  	    		pst.setDouble(2,r.getDouble(1));	    			
  	  	    		pst.setString(3,r.getString(2));	  	
  	  	    		pst.addBatch();
  	    			}
  	    	}else {
  	    		String q = "select close,date from dse.daily_price where ticker='"+ticker+"' and date>'"+lastDivDate+"'";
  	    		Statement s = con.createStatement();
  	    		ResultSet r = s.executeQuery(q);
  	    		while(r.next()) {
  	    			pst.setString(1,ticker);
  	  	    		pst.setDouble(2,r.getDouble(1));
  	  	    		pst.setString(3,r.getString(2)); 	  	    			
  	  	    		pst.addBatch();
  	    		}
  	  	    	for(Map.Entry<String, Double> entry : adjustedPriceMap.entrySet()) {
  	  	    		pst.setString(1,ticker);
  	  	    		pst.setDouble(2,entry.getValue());
  	  	    		pst.setString(3,entry.getKey()); 	  	    			
 	  	    		pst.addBatch();
  	  	    	}
  	    	}
  	    	Statement deleteStatement = con.createStatement();
  	    	deleteStatement.executeUpdate("delete from dse_analyzer.adjusted_price where code='"+ticker+"'");
  	    	pst.executeBatch(); 	    	
	}
	
	public static void populateVolume(String tic,Connection con ) throws SQLException {
		PreparedStatement pst = null;
  	    String ticker =tic;
  	    String upQ = "update dse_analyzer.adjusted_price set volume=? where code='"+ticker+"' and date=?";
  	    pst=con.prepareStatement(upQ);
  	    String q = "select volume,date from dse.daily_price where ticker='"+ticker+"' and date>='2015-07-25'";
  	    Statement s = con.createStatement();
	    ResultSet r = s.executeQuery(q);
	    while(r.next()) {
	    	pst.setFloat(1,r.getFloat(1));
	    	pst.setString(2,r.getString(2));	    			
	    	pst.addBatch();
	    }
	    pst.executeBatch();	    	
	}
	
	public static void updateDebtValue(String code, Double total_debt) throws ClassNotFoundException, SQLException {			
		Connection con = CommonUtils.connectLocal();
		PreparedStatement pst = null;			
		String ticker = code;
		Double debtValue = total_debt;																	
		String update_query = "update dse_analyzer.company set total_debt=? where code=?";
		pst=con.prepareStatement(update_query);							
		pst.setString(2,ticker);						
		if(debtValue!=null) {
			pst.setDouble(1,debtValue);
		}else {
			pst.setNull(1,java.sql.Types.NULL);
		}
		pst.execute();						
		con.close();		
	}
}

