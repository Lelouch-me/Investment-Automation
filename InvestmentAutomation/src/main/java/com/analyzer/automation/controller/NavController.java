package com.analyzer.automation.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.analyzer.automation.commonUtils.CommonUtils;
import com.analyzer.automation.domain.TempEpsDetails;
import com.analyzer.automation.domain.TempNav;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.model.TempEpsDetailsDto;
import com.analyzer.automation.model.TempNavDto;
import com.analyzer.automation.repository.UserActionRepository;
import com.analyzer.automation.servive.CompanyService;
import com.analyzer.automation.servive.MoveApprovedData;
import com.analyzer.automation.servive.TempEpsDetailsService;
import com.analyzer.automation.servive.TempNavService;
import com.analyzer.automation.servive.UpdateMFnavService;

@Controller
public class NavController {
	
	@Autowired
	private TempNavService tempNavService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserActionRepository userActionRepository;
	
	@RequestMapping(value = {"/nav"}, method = RequestMethod.GET)
	public String  nav(ModelMap modelMap) {
		List<TempNavDto> navList = tempNavService.getTempNav();	
		modelMap.addAttribute("tempNav", navList);
		System.out.println("showing NAV...."); 
		return "nav";
	}
	
	@PostMapping("/nav")
	@ResponseBody
	public String  deleteNav(String username) throws ClassNotFoundException, SQLException, ParseException {
		String tickers = MoveApprovedData.moveDataToArchive("nav");
		userActionRepository.save(new UserAction(username,"MOVE", "NAV", tickers));
		return "";
	}
	
	@RequestMapping(value = { "/updateNAV" }, method = RequestMethod.GET)
	public String updateNAV(ModelMap modelMap) {

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		modelMap.addAttribute("tickers", tickers);
		modelMap.addAttribute("tempNav", new TempNav());

		System.out.println("Update NAV");
		return "updateNAV";
	}
	
	@RequestMapping(value = { "/missingNAV" }, method = RequestMethod.POST)
	public String addNAV(ModelMap modelMap,@RequestParam("username") String username, String id, @RequestParam("ticker") String ticker,
			@RequestParam("nav") Double nav, @RequestParam("postDate") String postDate) {

//		if (mutualFund != null) {
//			tempWeeklyNavService.deleteTempWeeklyNav(mutualFund);
//		}
		
//		int id=tempEpsDetailsDto.getId();

		if (id ==null && ticker != null && nav != null && postDate != null) {
			tempNavService.addTempNav(ticker, nav, postDate, username);
			modelMap.addAttribute("status", "true");
		} else {
			 int idd=Integer.parseInt(id);
			tempNavService.updateTempNav(idd,ticker, nav, postDate,username);
			modelMap.addAttribute("status", "false");
		}
		return "redirect:/nav";

	}
	
	@RequestMapping("/editNAV/{id}")
	 public String showEditTempNAv(ModelMap modelMap,@PathVariable(name = "id") int id) {
		
        TempNav tmp = tempNavService.get(id);
		modelMap.addAttribute("tempNav", tmp);
		System.out.println("Edit NAV");
		return "editNav";	
	}
	
    @RequestMapping(value= {"/deleteNAV"}, method = RequestMethod.POST)
    public String deleteTempEpsDetails(int id, String username) {
    	TempNav tmp = tempNavService.get(id);
    	String ticker= tmp.getCode();
    	tempNavService.deleteTempNav(id,ticker,username);
        return  "redirect:/nav";
    }

}
