package com.analyzer.automation.servive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMFnavService {
	
	public static String updateValue() throws ClassNotFoundException, SQLException, ParseException {
				
	    Connection conLocal = connectLocal();
		String select_news_query="SELECT code,nav,date FROM dse_analyzer_loader.temp_weekly_nav";
		
		Statement stm= conLocal.createStatement();
		ResultSet rs= stm.executeQuery(select_news_query);
		
		String updateNav="update dse_analyzer.company set equities_per_share=?,last_update_time_pb=? where code=?";
		PreparedStatement pst=conLocal.prepareStatement(updateNav);
		String tickers = "";
		while(rs.next()) {
			
			String code=rs.getString(1);
			Double nav=rs.getDouble(2);
			String date=rs.getString(3);
			
			tickers += code + ",";
			String lastUpdateDate=changeDateFormate(date);			
			
			if(nav!=null) {
				pst.setDouble(1,nav);
			}else {
				pst.setNull(1, java.sql.Types.NULL);
			}
			
			if(lastUpdateDate!=null) {
				pst.setString(2,lastUpdateDate);
			}else {
				pst.setNull(2, java.sql.Types.NULL);
			}
			
			pst.setString(3, code);
			
			pst.execute();
			String insert_query="insert ignore into dse_analyzer_loader.weekly_nav_archive(code,nav,date) values ('"+code+"',"+nav+",'"+lastUpdateDate+"')";
			conLocal.prepareStatement(insert_query).execute();
			System.out.println(" Done for "+code+" "+nav+" "+lastUpdateDate );
			
		}
		conLocal.createStatement().executeUpdate("truncate table dse_analyzer_loader.temp_weekly_nav");
		conLocal.close();
		
		return tickers.substring(0,tickers.length()-1);
	}
	
//	public static Connection connectLocal() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://119.148.4.4:3306/dse_analyzer?rewriteBatchedStatements=true", "developer",
//		"developer4");
//		con.setAutoCommit(true);
//		return con;
//	}
	
	public static Connection connectLocal() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.100.12:3306/dse_analyzer?rewriteBatchedStatements=true","lrgb_loader",
		"developer4");
		con.setAutoCommit(true);
		return con;
	}
	
	public static String changeDateFormate(String date) throws ParseException {
		String today=date;
		Date d = new SimpleDateFormat("yyyy-MM-dd").parse(today);
		String newDate = new SimpleDateFormat("dd-MMM-yyyy").format(d);		
//		System.out.println("Previous format: "+today);
//		System.out.println("Changed format: "+newDate);		
		return newDate;
	}
	

}
