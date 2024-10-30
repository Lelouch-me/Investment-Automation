package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.DividendDetailsArchive;
import com.analyzer.automation.domain.EpsDetailsArchive;
import com.analyzer.automation.domain.LtmSalesArchive;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.DividendDetailsArchiveDto;
import com.analyzer.automation.model.EpsDetailsArchiveDto;
import com.analyzer.automation.model.LtmSalesArchiveDto;
import com.analyzer.automation.repository.DividendDetailsArchiveRepository;
import com.analyzer.automation.repository.EpsDetailsArchiveRepository;
import com.analyzer.automation.repository.LtmSalesArchiveRepository;
import com.analyzer.automation.repository.UserActionRepository;




@Service
public class DividendDetailsArchiveService {

@Autowired	
private DividendDetailsArchiveRepository dividendDetailsArchiveRepository;

@Autowired	
private UserActionRepository userActionRepository;
	
	public DividendDetailsArchiveService(DividendDetailsArchiveRepository dividendDetailsArchiveRepository, UserActionRepository userActionRepository) {
		super();
		this.dividendDetailsArchiveRepository = dividendDetailsArchiveRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<DividendDetailsArchiveDto> getDividend(String ticker){
		List<DividendDetailsArchiveDto> dividendDetailsDtoList = new ArrayList<DividendDetailsArchiveDto>();
		List<DividendDetailsArchive> dividends = dividendDetailsArchiveRepository.findAllByCodeOrderByYearDesc(ticker);
		if(dividends != null && !dividends.isEmpty()) {
			for(DividendDetailsArchive dividend : dividends) {
				DividendDetailsArchiveDto dividendDetailsArchiveDto = convertDomainToDto(dividend);
				if(dividendDetailsArchiveDto!=null) dividendDetailsDtoList.add(dividendDetailsArchiveDto);				
			}
		}
		return dividendDetailsDtoList;	
	}

	private DividendDetailsArchiveDto convertDomainToDto(DividendDetailsArchive dividendDetailsArchive) {
		return new DividendDetailsArchiveDto(dividendDetailsArchive.getId(),dividendDetailsArchive.getCode(),dividendDetailsArchive.getStockDiv(),dividendDetailsArchive.getCashDiv(),dividendDetailsArchive.getRecordDate(), dividendDetailsArchive.getYear());
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		ltmSalesArchiveRepository.deleteById(ticker);			
//	}
//	
    public DividendDetailsArchive get(int id) {
        return dividendDetailsArchiveRepository.findById(id).get();
    }
    
    public void deleteDividendDetailsArchive(int id, String ticker,String username) {
    	dividendDetailsArchiveRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "dividend", ticker));
    }

	public void addDividendDetailsArchives(String ticker, Double stockDiv, Double cashDiv,String recordDate,int year,String username) {
		dividendDetailsArchiveRepository.save(new DividendDetailsArchive(ticker,stockDiv,cashDiv,recordDate,year));
		userActionRepository.save(new UserAction(username, "Insert", "dividend", ticker));
	}
	
	public void updateDividendDetailsArchive(int id,String ticker, Double stockDiv, Double cashDiv,String recordDate,int year,String username) {
		dividendDetailsArchiveRepository.save(new DividendDetailsArchive(id,ticker,stockDiv,cashDiv,recordDate,year));
		userActionRepository.save(new UserAction(username, "Edit", "dividend", ticker));
	}
    public void save(DividendDetailsArchive dividendDetailsArchive) {
    	dividendDetailsArchiveRepository.save(dividendDetailsArchive);
    }
}
