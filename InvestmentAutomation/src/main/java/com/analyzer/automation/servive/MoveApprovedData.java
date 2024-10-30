package com.analyzer.automation.servive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.analyzer.automation.commonUtils.CommonUtils;

public class MoveApprovedData {

	static double ltmEps;
	static List<String> periods = new ArrayList<String>();
	static String[] quarterDetails = {"April-June","July-September","October-December","January-March","April-June","July-September","October-December"};
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {	
//		new MoveApprovedData().moveEPSDataToProd();		
//	}
	
	public static String moveDataToArchive(String type) throws ClassNotFoundException, SQLException {
		String code = "";
		
		String eps_truncate_query = "truncate table dse_analyzer_loader.temp_eps_details";
		String nav_truncate_query = "truncate table dse_analyzer_loader.temp_nav";
		String eps_insert_query = "insert ignore into dse_analyzer_loader.eps_details_archive(code,eps,post_date,year,period) "
				+ "select code,eps,post_date,year,period from dse_analyzer_loader.temp_eps_details";
		String nav_insert_query = "insert ignore into dse_analyzer_loader.nav_archive(code,nav,date) "
				+ "select code,nav, date from dse_analyzer_loader.temp_nav";


		Connection con = CommonUtils.connectLocal();
		if(type.equals("nav")) {
			code=moveNavDataToProd(con);		
			con.createStatement().executeUpdate(nav_insert_query);
			con.createStatement().executeUpdate(nav_truncate_query);
			
			
		}else {
			con.createStatement().executeUpdate(eps_insert_query);
			con.createStatement().executeUpdate(eps_truncate_query);
			code=moveEPSDataToProd();
		}
	
		con.close();
		
		return code;
	}
	
	public static String moveNavDataToProd(Connection con) throws SQLException {
		String ticker="";
		String select_query = "select * from dse_analyzer_loader.temp_nav";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(select_query);
		while(rs.next()) {
			ticker +=rs.getString(2)+",";
			String update_query = "update dse_analyzer.company set equities_per_share="+rs.getDouble(3)+", last_update_time_pb='"
					+rs.getString(4)+"' where code='"+rs.getString(2)+"'";
			
			Statement st1 = con.createStatement();
			st1.executeUpdate(update_query);
			
		}
		return ticker.substring(0,ticker.length()-1);
	}
	
	public static String moveEPSDataToProd() throws SQLException, ClassNotFoundException {
		String code="";
		Connection con = CommonUtils.connectLocal();
		List<String> tickers = getTickers(con);
//		List<String> tickers = new ArrayList<String>();
//		tickers.add("SINGERBD");
		
		
		int currentYear = LocalDate.now().getYear();
	
		for(String ticker : tickers) {
			Double[] dataArray = getAnnualEps(ticker,con);
			Double eps = dataArray[1];
			Double year = dataArray[0];
			if(eps==null) {
				int i = 0;
				ltmEps = 0;
				periods.clear();
				
				i = getCount(ticker,currentYear,i,con);
				
				if(i==4 && ltmEps!=0) {
					updateEPSInCompanyTable(con,ticker,ltmEps);
					System.out.println(ticker+" "+ltmEps);
					code+=ticker+",";
				}
			}else {
				updateEPSInCompanyTable(con,ticker,eps);
				populateFourthQuarterData(con,ticker,eps,year);
				code += ticker+",";
			}
		}
		con.close();
		return code.length() > 0 ? code.substring(0,code.length()-1) : code;
	}
	
	private static void populateFourthQuarterData(Connection con, String ticker, Double eps, Double year) throws SQLException {
		Map<String,Integer> quarterYearMap = new LinkedHashMap<String,Integer>();
		int fourthQuarterIndex = getFourthQuarterIndex(con,ticker);
		int yearInt = year.intValue();
		if(fourthQuarterIndex<7) {
			int thirdQuarterIndex = fourthQuarterIndex - 1;
			int secondQuarterIndex = fourthQuarterIndex - 2;
			int firstQuarterIndex = fourthQuarterIndex - 3;
			
			quarterYearMap.put(quarterDetails[firstQuarterIndex],firstQuarterIndex<3 ? yearInt - 1 : yearInt);
			quarterYearMap.put(quarterDetails[secondQuarterIndex],secondQuarterIndex<3 ? yearInt - 1 : yearInt);
			quarterYearMap.put(quarterDetails[thirdQuarterIndex],thirdQuarterIndex<3 ? yearInt - 1 : yearInt);
		}
		
		Double firstThreeQuarterEps = 0.0;
		for(Map.Entry<String,Integer> entry : quarterYearMap.entrySet()) {
			Double quarterWiseEps = getQuarterWiseEps(con,ticker,entry.getValue(),entry.getKey());
			firstThreeQuarterEps += quarterWiseEps;
		}
		
		Double fourthQuarterEps = eps - firstThreeQuarterEps;
		
		String insert_query = "insert ignore into dse_analyzer_loader.eps_details_archive(code,eps,post_date,year,period) values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(insert_query);
		ps.setString(1,ticker);
		ps.setDouble(2,fourthQuarterEps);
		ps.setString(3,LocalDate.now().toString());
		ps.setInt(4,yearInt);
		ps.setString(5,quarterDetails[fourthQuarterIndex]);
		
		ps.execute();
	}

	private static Double getQuarterWiseEps(Connection con, String ticker, Integer year, String period) throws SQLException {
		String q = "select eps from dse_analyzer_loader.eps_details_archive where code='"+ticker+"' and year="+year+" and period='"+period+"'";
		ResultSet rs = con.createStatement().executeQuery(q);
		Double eps = null;
		while(rs.next()) {
			eps = rs.getDouble(1);
		}
		
		return eps;
	}

	private static int getFourthQuarterIndex(Connection con, String ticker) throws SQLException {
		String q = "select year_end from dse_analyzer.company where code='"+ticker+"'";
		ResultSet rs = con.createStatement().executeQuery(q);
		rs.next();
		String year_end = rs.getString(1);
		int index = 7;
		if(year_end.toLowerCase().contains("mar")) index = 3;
		if(year_end.toLowerCase().contains("jun")) index = 4;
		if(year_end.toLowerCase().contains("sep")) index = 5;
		if(year_end.toLowerCase().contains("dec")) index = 6;
		
		return index;
	}

	private static void updateEPSInCompanyTable(Connection con, String ticker, Double eps) throws SQLException {
		String update_query = "update dse_analyzer.company set eps = "+eps+" where code = '"+ticker+"'";
		con.createStatement().executeUpdate(update_query);
	}
	
	
	private static Double[] getAnnualEps(String ticker, Connection con) throws SQLException {
		Double[] dataArray = new Double[2];
		String select_query = "select year,eps from dse_analyzer_loader.eps_details_archive where code='"+ticker+"' and post_date='"+
				LocalDate.now().toString()+"' and period='Annual'";
		ResultSet rs = con.createStatement().executeQuery(select_query);
		while(rs.next()) {
			dataArray[0] = rs.getDouble(1);
			dataArray[1] = rs.getDouble(2);
		}
		
		return dataArray;
	}

	public static int getCount(String ticker,int year,int count,Connection con) throws SQLException {
		String select_query = "select * from dse_analyzer_loader.eps_details_archive where code='"+ticker+"' and period!='Annual' and year="+year+
				" order by period desc";			
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(select_query);
		
		if(year>2019) {
			while(rs.next()) {
				if(!periods.contains(rs.getString(6))) {
					periods.add(rs.getString(6));
					ltmEps = ltmEps + rs.getDouble(3);
					count++;
				}
				if(count==4) break;					
			}		
			if(count<4) {
				count = getCount(ticker,--year,count,con);
			}
		}		
		return count;
	}
	private static List<String> getTickers(Connection con) throws SQLException{
		List<String> tickers = new ArrayList<String>();
		String select_query = "select distinct(code) from dse_analyzer_loader.eps_details_archive where post_date='"+LocalDate.now().toString()+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(select_query);
		while(rs.next()) {
			tickers.add(rs.getString(1));
		}		
		return tickers;
	}
}
