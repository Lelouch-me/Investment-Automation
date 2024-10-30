package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.TempEpsDetails;
import com.analyzer.automation.domain.TempNav;
import com.analyzer.automation.domain.TempWeeklyNav;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.TempEpsDetailsDto;
import com.analyzer.automation.model.TempNavDto;
import com.analyzer.automation.model.TempWeeklyNavDto;
import com.analyzer.automation.repository.TempEpsDetailsRepository;
import com.analyzer.automation.repository.TempNavRepository;
import com.analyzer.automation.repository.TempWeeklyNavRepository;
import com.analyzer.automation.repository.UserActionRepository;




@Service
public class TempWeeklyNavService {

@Autowired	
private TempWeeklyNavRepository tempWeeklyNavRepository;

@Autowired	
private UserActionRepository userActionRepository;
	
	public TempWeeklyNavService(TempWeeklyNavRepository tempWeeklyNavRepository, UserActionRepository userActionRepository) {
		super();
		this.tempWeeklyNavRepository = tempWeeklyNavRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<TempWeeklyNavDto> getTempWeeklyNav(){
		List<TempWeeklyNavDto> tempWeeklyNavDtoList = new ArrayList<TempWeeklyNavDto>();
		List<TempWeeklyNav> tempWeeklyNavs = tempWeeklyNavRepository.findAll();
		if(tempWeeklyNavs != null && !tempWeeklyNavs.isEmpty()) {
			for(TempWeeklyNav tempWeeklyNav : tempWeeklyNavs) {
				TempWeeklyNavDto tempWeeklyNavDto = convertDomainToDto(tempWeeklyNav);
				if(tempWeeklyNavDto!=null) tempWeeklyNavDtoList.add(tempWeeklyNavDto);				
			}
		}
		return tempWeeklyNavDtoList;	
	}

	private TempWeeklyNavDto convertDomainToDto(TempWeeklyNav tempWeeklyNav) {
		return new TempWeeklyNavDto(tempWeeklyNav.getId(),tempWeeklyNav.getCode(),tempWeeklyNav.getNav(),tempWeeklyNav.getDate());
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		tempEpsDetailsRepository.deleteById(ticker);			
//	}
	
    public TempWeeklyNav get(int id) {
        return tempWeeklyNavRepository.findById(id).get();
    }
    
    public void deleteTempWeeklyNav(int id, String ticker,String username) {
    	tempWeeklyNavRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "MF NAV", ticker));
    }

	public void addTempWeeklyNav(String ticker, Double nav,String postDate,String username) {
		tempWeeklyNavRepository.save(new TempWeeklyNav(ticker,nav,postDate));
		userActionRepository.save(new UserAction(username, "Insert", "MF NAV", ticker));
	}
	
	public void updateTempWeeklyNav(int id,String ticker,Double eps, String postDate, String username) {
		tempWeeklyNavRepository.save(new TempWeeklyNav(id,ticker,eps,postDate));
		userActionRepository.save(new UserAction(username, "Edit", "MF NAV", ticker));
	}
    public void save(TempWeeklyNav tempWeeklyNav) {
    	tempWeeklyNavRepository.save(tempWeeklyNav);
    }
}
