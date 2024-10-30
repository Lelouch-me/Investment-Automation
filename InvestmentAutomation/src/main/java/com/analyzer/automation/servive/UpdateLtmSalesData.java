package com.analyzer.automation.servive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.analyzer.automation.commonUtils.CommonUtils;

public class UpdateLtmSalesData {
	static double ltmSales;
	static List<String> periods = new ArrayList<String>();
	
	public static void moveLtmSalesDataToProd(String ticker) throws SQLException, ClassNotFoundException {
		Connection con = CommonUtils.connectLocal();	
		int currentYear = LocalDate.now().getYear();	
		int i = 0;
		ltmSales = 0;
		periods.clear();			
		i = getCount(ticker,currentYear,i,con);
			
		if(i==4 && ltmSales!=0) {
			String query = "select out_shares from dse_analyzer.daily_company_info where code ='"+ticker+"'";							
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			double out_shares = 0.0;
			while(rs.next()) {
				out_shares = rs.getDouble(1);
			}				
			Double sales_per_share = null;
			if(out_shares != 0.0) {
				sales_per_share = ltmSales/out_shares;
			}				
			String update_query = "update dse_analyzer.company set sales_per_share=? where code='"+ticker+"'";				
			PreparedStatement pst=con.prepareStatement(update_query);
				
			if(sales_per_share!=null) {
				pst.setDouble(1,sales_per_share);
			}else {
				pst.setNull(1, java.sql.Types.NULL);
			}
			pst.execute();
			System.out.println(ticker+" "+ltmSales);		
			con.close();
		}
	}
	
	public static int getCount(String ticker,int year,int count,Connection con) throws SQLException {
		String select_query = "select * from dse_analyzer_loader.ltm_sales_archive where code='"+ticker+"' and period!='Annual' and year="+year+
				" order by period desc";			
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(select_query);
		
		if(year>2019) {
			while(rs.next()) {
				if(!periods.contains(rs.getString(6))) {
					periods.add(rs.getString(6));
					ltmSales = ltmSales + rs.getDouble(3);
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
}
