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
import org.springframework.web.servlet.ModelAndView;

import com.analyzer.automation.commonUtils.CommonUtils;
import com.analyzer.automation.domain.TempEpsDetails;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.CompanyDto;
import com.analyzer.automation.model.TempEpsDetailsDto;
import com.analyzer.automation.repository.UserActionRepository;
import com.analyzer.automation.servive.CompanyService;
import com.analyzer.automation.servive.MoveApprovedData;
import com.analyzer.automation.servive.TempEpsDetailsService;
import com.analyzer.automation.servive.UpdateMFnavService;

@Controller
public class EpsController {
	
	@Autowired
	private TempEpsDetailsService tempEpsDetailsService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserActionRepository userActionRepository;
	
	@RequestMapping(value = {"/eps"}, method = RequestMethod.GET)
	public String  eps(ModelMap modelMap) {
		List<TempEpsDetailsDto> epsList = tempEpsDetailsService.getTempEpsDetails();	
		modelMap.addAttribute("tempEpsDetails", epsList);
		System.out.println("showing LTM EPS...."); 
		return "eps";
	}
	
	@PostMapping("/eps")
	@ResponseBody
	public String deleteEps(String username) throws ClassNotFoundException, SQLException, ParseException {
		String tickers = MoveApprovedData.moveDataToArchive("eps");
		userActionRepository.save(new UserAction(username,"MOVE", "EPS", tickers));
		return "";
	}
	
	@RequestMapping(value = { "/updateEPS" }, method = RequestMethod.GET)
	public String updateEPS(ModelMap modelMap) {

		List<CompanyDto> tickerList = companyService.getTicker();
		String[] tickers = CommonUtils.getTickerArray(tickerList);
		modelMap.addAttribute("tickers", tickers);
		modelMap.addAttribute("tempEpsDetails", new TempEpsDetails());

		System.out.println("Update EPS");
		return "updateEPS";
	}
	
	@RequestMapping(value = { "/missingEPS" }, method = RequestMethod.POST)
	public String addEPS(ModelMap modelMap,@RequestParam("username") String username, String id, @RequestParam("ticker") String ticker,
			@RequestParam("eps") Double eps, @RequestParam("postDate") String postDate, @RequestParam("year") int year, @RequestParam("period") String period) {

		if (id ==null && ticker != null && eps != null && postDate != null && year > 0 && period != null ) {
			tempEpsDetailsService.addTempEpsDetails(ticker, eps, postDate, year, period,username);
			modelMap.addAttribute("status", "true");
		} else {
			 int idd=Integer.parseInt(id);
			tempEpsDetailsService.updateTempEpsDetails(idd,ticker, eps, postDate, year, period,username);
			modelMap.addAttribute("status", "false");
		}
		return "redirect:/eps";

	}
	
	@RequestMapping("/editEPS/{id}")
	 public String showEditTempEpsDetails(ModelMap modelMap,@PathVariable(name = "id") int id) {
		
        TempEpsDetails tmp = tempEpsDetailsService.get(id);
		modelMap.addAttribute("tempEpsDetails", tmp);
		System.out.println("Edit EPS");
		return "editEps";	
	}
	
    @RequestMapping(value= {"/deleteEPS"}, method = RequestMethod.POST)
    public String deleteTempEpsDetails(int id, String username) {
    	TempEpsDetails tmp = tempEpsDetailsService.get(id);
    	String ticker= tmp.getCode();
    	tempEpsDetailsService.deleteTempEpsDetails(id,ticker,username);
        return  "redirect:/eps";
    }
}
