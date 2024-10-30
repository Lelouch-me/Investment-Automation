package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.analyzer.automation.domain.LtmSalesArchive;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.LtmSalesArchiveDto;
import com.analyzer.automation.repository.LtmSalesArchiveRepository;
import com.analyzer.automation.repository.UserActionRepository;




@Service
public class LtmSalesArchiveService {

@Autowired	
private LtmSalesArchiveRepository ltmSalesArchiveRepository;

@Autowired	
private UserActionRepository userActionRepository;
	
	public LtmSalesArchiveService(LtmSalesArchiveRepository ltmSalesArchiveRepository, UserActionRepository userActionRepository) {
		super();
		this.ltmSalesArchiveRepository = ltmSalesArchiveRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<LtmSalesArchiveDto> getLtmSales(String ticker){
		List<LtmSalesArchiveDto> tempEpsDetailsDtoList = new ArrayList<LtmSalesArchiveDto>();
		List<LtmSalesArchive> ltmSaless = ltmSalesArchiveRepository.findAllByCodeOrderByYearDesc(ticker);
		if(ltmSaless != null && !ltmSaless.isEmpty()) {
			for(LtmSalesArchive ltmSales : ltmSaless) {
				LtmSalesArchiveDto ltmSlaesArchiveDto = convertDomainToDto(ltmSales);
				if(ltmSlaesArchiveDto!=null) tempEpsDetailsDtoList.add(ltmSlaesArchiveDto);				
			}
		}
		return tempEpsDetailsDtoList;	
	}

	private LtmSalesArchiveDto convertDomainToDto(LtmSalesArchive ltmSalesArchive) {
		return new LtmSalesArchiveDto(ltmSalesArchive.getId(),ltmSalesArchive.getCode(),ltmSalesArchive.getLtmSales(),ltmSalesArchive.getPostDate(), ltmSalesArchive.getYear(),ltmSalesArchive.getPeriod() );
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		ltmSalesArchiveRepository.deleteById(ticker);			
//	}
//	
    public LtmSalesArchive get(int id) {
        return ltmSalesArchiveRepository.findById(id).get();
    }
    
    public void deleteLtmSalesArchive(int id, String ticker,String username) {
    	ltmSalesArchiveRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "LTM Sales", ticker));
    }

	public void addLtmSalesArchives(String ticker, Double ltmSales,String postDate,int year,String period,String username) {
		ltmSalesArchiveRepository.save(new LtmSalesArchive(ticker,ltmSales,postDate,year,period));
		userActionRepository.save(new UserAction(username, "Insert", "LTM Sales", ticker));
	}
	
	public void updateLtmSalesArchive(int id,String ticker,Double ltmSales, String postDate,int year, String period, String username) {
		ltmSalesArchiveRepository.save(new LtmSalesArchive(id,ticker,ltmSales,postDate,year,period));
		userActionRepository.save(new UserAction(username, "Edit", "LTM Sales", ticker));
	}
    public void save(LtmSalesArchive ltmSalesArchive) {
    	ltmSalesArchiveRepository.save(ltmSalesArchive);
    }
}
