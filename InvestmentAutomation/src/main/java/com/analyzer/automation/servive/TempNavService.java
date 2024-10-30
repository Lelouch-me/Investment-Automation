package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.TempNav;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.TempNavDto;
import com.analyzer.automation.repository.TempNavRepository;
import com.analyzer.automation.repository.UserActionRepository;

@Service
public class TempNavService {

	private TempNavRepository tempNavRepository;

	private UserActionRepository userActionRepository;
	
	@Autowired
	public TempNavService(TempNavRepository tempNavRepository, UserActionRepository userActionRepository) {
		super();
		this.tempNavRepository = tempNavRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<TempNavDto> getTempNav(){
		List<TempNavDto> tempNavDtoList = new ArrayList<TempNavDto>();
		List<TempNav> tempNavs = tempNavRepository.findAll();
		if(tempNavs != null && !tempNavs.isEmpty()) {
			for(TempNav tempNav : tempNavs) {
				TempNavDto tempNavDto = convertDomainToDto(tempNav);
				if(tempNavDto!=null) tempNavDtoList.add(tempNavDto);				
			}
		}
		return tempNavDtoList;	
	}

	private TempNavDto convertDomainToDto(TempNav tempNav) {
		return new TempNavDto(tempNav.getId(),tempNav.getCode(),tempNav.getNav(),tempNav.getDate());
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		tempEpsDetailsRepository.deleteById(ticker);			
//	}
	
    public TempNav get(int id) {
        return tempNavRepository.findById(id).get();
    }
    
    public void deleteTempNav(int id, String ticker,String username) {
    	tempNavRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "NAV", ticker));
    }

	public void addTempNav(String ticker, Double eps,String postDate,String username) {
		tempNavRepository.save(new TempNav(ticker,eps,postDate));
		userActionRepository.save(new UserAction(username, "Insert", "NAV", ticker));
	}
	
	public void updateTempNav(int id,String ticker,Double eps, String postDate, String username) {
		tempNavRepository.save(new TempNav(id,ticker,eps,postDate));
		userActionRepository.save(new UserAction(username, "Edit", "NAV", ticker));
	}
    public void save(TempNav tempNav) {
    	tempNavRepository.save(tempNav);
    }
}
