package com.analyzer.automation.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.analyzer.automation.commonUtils.CommonUtils;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.repository.UserActionRepository;
import com.analyzer.automation.servive.CompanyService;
import com.analyzer.automation.servive.LtmSalesArchiveService;
import com.analyzer.automation.servive.UpdateLtmSalesData;
import com.analyzer.automation.servive.UpdateProductionData;


@Controller
public class HomeController {
	
	@Autowired
	UserActionRepository userActionRepository;
	
	@Autowired
	private LtmSalesArchiveService ltmSalesArchiveService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login() {
		System.out.println("log in");
		return "login";
	}
	
	@RequestMapping(value = { "/editLtmSales" }, method = RequestMethod.GET)
	public String editLtm(ModelMap modelMap) {

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		modelMap.addAttribute("tickers", tickers);
		//modelMap.addAttribute("tempEpsDetails", new TempEpsDetails());

		System.out.println("LTM Sales");
		return "editLtmSales";
	}
	
	@RequestMapping(value = { "/insertLtmSales" }, method = RequestMethod.POST)
	public String addLtemSales(ModelMap modelMap,@RequestParam("username") String username, String id, @RequestParam("ticker") String ticker,
			@RequestParam("ltmSales") Double ltmSales, @RequestParam("postDate") String postDate, @RequestParam("year") int year, @RequestParam("period") String period) throws ClassNotFoundException, SQLException {

		if (id ==null && ticker != null && ltmSales != null && postDate != null && year > 0 && period != null ) {
			ltmSalesArchiveService.addLtmSalesArchives(ticker, ltmSales, postDate, year, period,username);
			UpdateLtmSalesData.moveLtmSalesDataToProd(ticker);
			modelMap.addAttribute("status", "true");
		}
		return "redirect:/ltmSalesInfo";
	}


//	@RequestMapping(value = {"/mutualFundNav"}, method = RequestMethod.GET)
//	public String  mutualFundNav(ModelMap modelMap) {
//		List<TempWeeklyNavDto> tempWeeklyNavList = tempWeeklyNavService.getTempWeeklyNavs();		
//		modelMap.addAttribute("tempWeeklyNavs", tempWeeklyNavList);
//		System.out.println("showing Mutual Fund Weekly NAV...."); 
//		return "MutualFundNav";
//	}
	
//	@RequestMapping(value = {"/nav"}, method = RequestMethod.GET)
//	public String  Nav(ModelMap modelMap) {
//		List<TempNavDto> navList = tempNavService.getTempNav();		
//		modelMap.addAttribute("tempNavs", navList);
//		System.out.println("showing  NAV...."); 
//		return "nav";
//	}
	
	@RequestMapping(value = { "/divYield" }, method = RequestMethod.GET)
	public String editDivYield(ModelMap modelMap) {

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		modelMap.addAttribute("tickers", tickers);
		//modelMap.addAttribute("tempEpsDetails", new TempEpsDetails());

		System.out.println("Corporate Declaration");
		return "divYield";
	}
	
	@RequestMapping(value = { "/divYield" }, method = RequestMethod.POST)
	public String updateDivYield(ModelMap modelMap,@RequestParam("username") String username, String id, @RequestParam("ticker") String ticker,
			@RequestParam(value="stockDiv", required=false) Double stockDiv, @RequestParam(value="cashDiv", required = false) Double cashDiv, @RequestParam("recordDate") String recordDate, @RequestParam("year") int year) throws ClassNotFoundException, SQLException {

		if ((ticker != null &&  recordDate != null && year>0) && ( cashDiv!=null || stockDiv!=null)) {
			UpdateProductionData.populateAdjustedPrice(ticker, stockDiv, cashDiv, recordDate, year);
			userActionRepository.save(new UserAction(username, "Insert", "Divident", ticker));
			modelMap.addAttribute("status", "true");
		}
		return "redirect:/divYield";
	}
	
	@RequestMapping(value = { "/debtToEquity" }, method = RequestMethod.GET)
	public String editDeptToEquity(ModelMap modelMap) {

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		modelMap.addAttribute("tickers", tickers);
		//modelMap.addAttribute("tempEpsDetails", new TempEpsDetails());

		System.out.println("Edit Debt To Equity");
		return "debtToEquity";
	}
	
	@RequestMapping(value = { "/insertDebtValue" }, method = RequestMethod.POST)
	public String addDebtValue(ModelMap modelMap,@RequestParam("username") String username, String id, @RequestParam("ticker") String ticker,
			@RequestParam("totalDebt") Double totalDebt, @RequestParam("postDate") String postDate) throws ClassNotFoundException, SQLException {

		if (id ==null && ticker != null && totalDebt != null && postDate != null) {
			UpdateProductionData.updateDebtValue(ticker,totalDebt);
			userActionRepository.save(new UserAction(username, "Insert", "Debt", ticker));
			System.out.println("Intsert Debt" + totalDebt +" by " + username );
			modelMap.addAttribute("status", "true");
		}
		return "redirect:/debtToEquity";
	}

}
