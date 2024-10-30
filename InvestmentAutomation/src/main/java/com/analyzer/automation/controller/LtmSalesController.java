package com.analyzer.automation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.analyzer.automation.commonUtils.CommonUtils;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.model.LtmSalesArchiveDto;
import com.analyzer.automation.servive.CompanyService;
import com.analyzer.automation.servive.LtmSalesArchiveService;

@Controller
public class LtmSalesController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private LtmSalesArchiveService ltmSalesArchiveService;
	
	@RequestMapping(value = { "/ltmSalesInfo" }, method = RequestMethod.GET)
	public String editLtmSales(ModelMap modelMap, String ticker) { 

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		modelMap.addAttribute("tickers", tickers);
		System.out.println("showing Sales Info....");
		return "ltmSalesInfo";
	}
	
	@RequestMapping(value = { "/ltmSalesInfo" }, method = RequestMethod.POST)
	public String editLtmSalesCheck(ModelMap modelMap, String ticker) { 

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		List<LtmSalesArchiveDto> ltmSalesList = ltmSalesArchiveService.getLtmSales(ticker);	
		modelMap.addAttribute("ltmSalesArchive", ltmSalesList);
		modelMap.addAttribute("tickers", tickers);
		System.out.println("Check Sales Info");
		return "ltmSalesInfo";
	}
}
