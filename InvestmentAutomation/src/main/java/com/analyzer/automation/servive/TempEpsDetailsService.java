package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.TempEpsDetails;
import com.analyzer.automation.domain.TempNav;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.TempEpsDetailsDto;
import com.analyzer.automation.model.TempNavDto;
import com.analyzer.automation.repository.TempEpsDetailsRepository;
import com.analyzer.automation.repository.TempNavRepository;
import com.analyzer.automation.repository.UserActionRepository;




@Service
public class TempEpsDetailsService {

@Autowired	
private TempEpsDetailsRepository tempEpsDetailsRepository;

@Autowired	
private UserActionRepository userActionRepository;
	
	public TempEpsDetailsService(TempEpsDetailsRepository tempEpsDetailsRepository, UserActionRepository userActionRepository) {
		super();
		this.tempEpsDetailsRepository = tempEpsDetailsRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<TempEpsDetailsDto> getTempEpsDetails(){
		List<TempEpsDetailsDto> tempEpsDetailsDtoList = new ArrayList<TempEpsDetailsDto>();
		List<TempEpsDetails> tempEpsDetailss = tempEpsDetailsRepository.findAll();
		if(tempEpsDetailss != null && !tempEpsDetailss.isEmpty()) {
			for(TempEpsDetails tempEpsDetails : tempEpsDetailss) {
				TempEpsDetailsDto tempEpsDetailsDto = convertDomainToDto(tempEpsDetails);
				if(tempEpsDetailsDto!=null) tempEpsDetailsDtoList.add(tempEpsDetailsDto);				
			}
		}
		return tempEpsDetailsDtoList;	
	}

	private TempEpsDetailsDto convertDomainToDto(TempEpsDetails tempEpsDetails) {
		return new TempEpsDetailsDto(tempEpsDetails.getId(),tempEpsDetails.getCode(),tempEpsDetails.getEps(),tempEpsDetails.getPostDate(), tempEpsDetails.getYear(),tempEpsDetails.getPeriod() );
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		tempEpsDetailsRepository.deleteById(ticker);			
//	}
	
    public TempEpsDetails get(int id) {
        return tempEpsDetailsRepository.findById(id).get();
    }
    
    public void deleteTempEpsDetails(int id, String ticker,String username) {
    	tempEpsDetailsRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "EPS", ticker));
    }

	public void addTempEpsDetails(String ticker, Double eps,String postDate,int year,String period,String username) {
		tempEpsDetailsRepository.save(new TempEpsDetails(ticker,eps,postDate,year,period));
		userActionRepository.save(new UserAction(username, "Insert", "EPS", ticker));
	}
	
	public void updateTempEpsDetails(int id,String ticker,Double eps, String postDate,int year, String period, String username) {
		tempEpsDetailsRepository.save(new TempEpsDetails(id,ticker,eps,postDate,year,period));
		userActionRepository.save(new UserAction(username, "Edit", "EPS", ticker));
	}
    public void save(TempEpsDetails tempEpsDetails) {
    	tempEpsDetailsRepository.save(tempEpsDetails);
    }
}
