package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.TempDividend;
import com.analyzer.automation.domain.TempEpsDetails;
import com.analyzer.automation.domain.TempNav;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.TempDividendDto;
import com.analyzer.automation.model.TempEpsDetailsDto;
import com.analyzer.automation.model.TempNavDto;
import com.analyzer.automation.repository.TempDividendRepository;
import com.analyzer.automation.repository.TempEpsDetailsRepository;
import com.analyzer.automation.repository.TempNavRepository;
import com.analyzer.automation.repository.UserActionRepository;




@Service
public class TempDividendService {

@Autowired	
private TempDividendRepository tempDividendRepository;

@Autowired	
private UserActionRepository userActionRepository;
	
	public TempDividendService(TempDividendRepository tempDividendRepository, UserActionRepository userActionRepository) {
		super();
		this.tempDividendRepository = tempDividendRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<TempDividendDto> getTempDividend(){
		List<TempDividendDto> tempDividendDtoList = new ArrayList<TempDividendDto>();
		List<TempDividend> tempDividends = tempDividendRepository.findAll();
		if(tempDividends != null && !tempDividends.isEmpty()) {
			for(TempDividend tempDividend : tempDividends) {
				TempDividendDto tempDividendDto = convertDomainToDto(tempDividend);
				if(tempDividendDto!=null) tempDividendDtoList.add(tempDividendDto);				
			}
		}
		return tempDividendDtoList;	
	}

	private TempDividendDto convertDomainToDto(TempDividend tempDividend) {
		return new TempDividendDto(tempDividend.getId(),tempDividend.getCode(),tempDividend.getCashDiv(),tempDividend.getStockDiv(), tempDividend.getDate(),tempDividend.getYear());
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		tempEpsDetailsRepository.deleteById(ticker);			
//	}
	
    public TempDividend get(int id) {
        return tempDividendRepository.findById(id).get();
    }
    
    public void deleteTempDividend(int id, String ticker,String username) {
    	tempDividendRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "Dividend", ticker));
    }

	public void addTempDividend(String ticker, Double cashDiv, Double stockDiv,String date,int year,String username) {
		tempDividendRepository.save(new TempDividend(ticker,cashDiv,stockDiv,date,year));
		userActionRepository.save(new UserAction(username, "Insert", "Dividend", ticker));
	}
	
	public void updateTempDividend(int id,String ticker, Double cashDiv, Double stockDiv,String date, int year,String username) {
		tempDividendRepository.save(new TempDividend(id,ticker,cashDiv,stockDiv,date, year));
		userActionRepository.save(new UserAction(username, "Edit", "Dividend", ticker));
	}
    public void save(TempDividend tempDividend) {
    	tempDividendRepository.save(tempDividend);
    }
}
