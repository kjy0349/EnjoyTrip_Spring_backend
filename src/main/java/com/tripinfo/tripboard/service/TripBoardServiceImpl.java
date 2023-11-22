package com.tripinfo.tripboard.service;

import com.tripinfo.tripboard.dto.TripBoardRouteDetailDto;
import com.tripinfo.tripboard.dto.TripBoardDto;
import com.tripinfo.tripboard.dto.TripRouteDto;
import com.tripinfo.tripboard.mapper.TripBoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripBoardServiceImpl {
    private final TripBoardMapper tripBoardMapper;

    public List<TripBoardDto> getAllTripArticles(int pgno, int pageSize) {
    	int start = pgno * pageSize - pageSize;
    	int listsize = pageSize;
    	
        return tripBoardMapper.getAllTripArticles(start, listsize);
    }

    public int insertTripArticle(TripBoardDto tripBoardDto) { return tripBoardMapper.insertTripArticle(tripBoardDto);}

    public TripBoardDto getTripArticleByNumber(int articleNo) {
    	tripBoardMapper.updateTripHit(articleNo);
    	return tripBoardMapper.getTripArticleByNumber(articleNo);
    }
    
    public int deleteTripArticle(int articleNo) { return tripBoardMapper.deleteTripArticle(articleNo);}
    
    public int modifyTripArticle(TripBoardDto article) { return tripBoardMapper.modifyTripArticle(article);}
    
    public List<TripBoardDto> getTripArticlesBySubject(String subject) {return tripBoardMapper.getTripArticlesBySubject(subject);}

	public int getTripTotal() {
		return tripBoardMapper.getTotal();
	}

    public List<TripRouteDto> getTripRouteByUserId(String userId) { return tripBoardMapper.getTripRouteByUserId(userId);}

    public List<TripBoardRouteDetailDto> getRouteDetails(int planId) {return tripBoardMapper.getRouteDetails(planId);}
}
