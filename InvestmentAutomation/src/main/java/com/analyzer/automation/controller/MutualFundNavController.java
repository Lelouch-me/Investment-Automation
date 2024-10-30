package com.analyzer.automation.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.analyzer.automation.commonUtils.CommonUtils;
import com.analyzer.automation.domain.TempWeeklyNav;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.model.TempWeeklyNavDto;
import com.analyzer.automation.repository.UserActionRepository;
import com.analyzer.automation.servive.CompanyService;
import com.analyzer.automation.servive.TempWeeklyNavService;
import com.analyzer.automation.servive.UpdateMFnavService;

@Controller
public class MutualFundNavController {
	
	@Autowired
	private TempWeeklyNavService tempWeeklyNavService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserActionRepository userActionRepository;
	
	@RequestMapping(value = {"/mutualFundNav"}, method = RequestMethod.GET)
	public String  weeklynav(ModelMap modelMap) {
		List<TempWeeklyNavDto> weeklyNavList = tempWeeklyNavService.getTempWeeklyNav();	
		modelMap.addAttribute("tempWeeklyNav", weeklyNavList);
		System.out.println("showing Weekly MF NAV...."); 
		return "MutualFundNav";
	}
	
	@PostMapping("/mutualFundNav")
	@ResponseBody
	public String  deleteweeklynav(String username) throws ClassNotFoundException, SQLException, ParseException {
		String tickers = UpdateMFnavService.updateValue();
		userActionRepository.save(new UserAction(username, "MOVE", "MF NAV", tickers));
		return "";
	}
	
	@RequestMapping(value = { "/updateWeeklyNAV" }, method = RequestMethod.GET)
	public String updateWeeklyNAV(ModelMap modelMap) {

		List<CompanyDto> mfTickerList = companyService.getTickerMF();
		String[] tickers = CommonUtils.getTickerArray(mfTickerList);
		modelMap.addAttribute("tickers", tickers);
		modelMap.addAttribute("tempWeeklyNav", new TempWeeklyNav());

		System.out.println("Update NAV");
		return "updateMFnav";
	}
	
	@RequestMapping(value = { "/missingWeeklyNAV" }, method = RequestMethod.POST)
	public String addWeeklyNAV(ModelMap modelMap,@RequestParam("username") String username, String id, @RequestParam("ticker") String ticker,
			@RequestParam("nav") Double nav, @RequestParam("postDate") String postDate) {

//		if (mutualFund != null) {
//			tempWeeklyNavService.deleteTempWeeklyNav(mutualFund);
//		}
		
//		int id=tempEpsDetailsDto.getId();

		if (id ==null && ticker != null && nav != null && postDate != null) {
			tempWeeklyNavService.addTempWeeklyNav(ticker, nav, postDate, username);
			modelMap.addAttribute("status", "true");
		} else {
			 int idd=Integer.parseInt(id);
			tempWeeklyNavService.updateTempWeeklyNav(idd,ticker, nav, postDate,username);
			modelMap.addAttribute("status", "false");
		}
		return "redirect:/mutualFundNav";

	}
	
	@RequestMapping("/editWeeklyNAV/{id}")
	public String showEditTempWeeklyNAv(ModelMap modelMap,@PathVariable(name = "id") int id) {
		
        TempWeeklyNav tmp = tempWeeklyNavService.get(id);
		modelMap.addAttribute("tempWeeklyNav", tmp);
		System.out.println("Edit Weekly MF NAV");
		return "editMFnav";	
	}
	
	@PostMapping("/deleteWeeklyNAV")
	@ResponseBody
    public String deleteTempEpsDetails( int id, String username) {
    	TempWeeklyNav tmp = tempWeeklyNavService.get(id);
    	String ticker= tmp.getCode();
    	tempWeeklyNavService.deleteTempWeeklyNav(id,ticker,username);
    	return "";
    }

}
