package com.analyzer.automation.servive;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analyzer.automation.domain.EpsDetailsArchive;
import com.analyzer.automation.domain.LtmSalesArchive;
import com.analyzer.automation.domain.UserAction;
import com.analyzer.automation.model.EpsDetailsArchiveDto;
import com.analyzer.automation.model.LtmSalesArchiveDto;
import com.analyzer.automation.repository.EpsDetailsArchiveRepository;
import com.analyzer.automation.repository.LtmSalesArchiveRepository;
import com.analyzer.automation.repository.UserActionRepository;




@Service
public class EpsDetailsArchiveService {

@Autowired	
private EpsDetailsArchiveRepository epsDetailsArchiveRepository;

@Autowired	
private UserActionRepository userActionRepository;
	
	public EpsDetailsArchiveService(EpsDetailsArchiveRepository epsDetailsArchiveRepository, UserActionRepository userActionRepository) {
		super();
		this.epsDetailsArchiveRepository = epsDetailsArchiveRepository;
		this.userActionRepository = userActionRepository;
	}
	
	public List<EpsDetailsArchiveDto> getEps(String ticker){
		List<EpsDetailsArchiveDto> epsDetailsDtoList = new ArrayList<EpsDetailsArchiveDto>();
		List<EpsDetailsArchive> ltmEpss = epsDetailsArchiveRepository.findAllByCodeOrderByYearDesc(ticker);
		if(ltmEpss != null && !ltmEpss.isEmpty()) {
			for(EpsDetailsArchive ltmEps : ltmEpss) {
				EpsDetailsArchiveDto epsDetailsArchiveDto = convertDomainToDto(ltmEps);
				if(epsDetailsArchiveDto!=null) epsDetailsDtoList.add(epsDetailsArchiveDto);				
			}
		}
		return epsDetailsDtoList;	
	}

	private EpsDetailsArchiveDto convertDomainToDto(EpsDetailsArchive epsDetailsArchive) {
		return new EpsDetailsArchiveDto(epsDetailsArchive.getId(),epsDetailsArchive.getCode(),epsDetailsArchive.getEps(),epsDetailsArchive.getPostDate(), epsDetailsArchive.getYear(),epsDetailsArchive.getPeriod() );
	}
	
//	public void deleteTempEpsDetails(String ticker) {
//		ltmSalesArchiveRepository.deleteById(ticker);			
//	}
//	
    public EpsDetailsArchive get(int id) {
        return epsDetailsArchiveRepository.findById(id).get();
    }
    
    public void deleteEpsDetailsArchive(int id, String ticker,String username) {
    	epsDetailsArchiveRepository.deleteById(id);
    	userActionRepository.save(new UserAction(username, "Delete", "Eps", ticker));
    }

	public void addEpsDetailsArchives(String ticker, Double ltmEps,String postDate,int year,String period,String username) {
		epsDetailsArchiveRepository.save(new EpsDetailsArchive(ticker,ltmEps,postDate,year,period));
		userActionRepository.save(new UserAction(username, "Insert", "Eps", ticker));
	}
	
	public void updateEpsDetailsArchive(int id,String ticker,Double ltmEps, String postDate,int year, String period, String username) {
		epsDetailsArchiveRepository.save(new EpsDetailsArchive(id,ticker,ltmEps,postDate,year,period));
		userActionRepository.save(new UserAction(username, "Edit", "Eps", ticker));
	}
    public void save(EpsDetailsArchive epsDetailsArchive) {
    	epsDetailsArchiveRepository.save(epsDetailsArchive);
    }
}
