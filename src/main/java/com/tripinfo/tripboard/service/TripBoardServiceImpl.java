package com.tripinfo.tripboard.service;

import com.tripinfo.tripboard.dto.TripBoardRouteDetailDto;
import com.tripinfo.member.MemberInfoDto;
import com.tripinfo.member.model.mapper.MemberMapper;
import com.tripinfo.tripboard.dto.TripBoardDto;
import com.tripinfo.tripboard.dto.TripRouteDto;
import com.tripinfo.tripboard.mapper.TripBoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class TripBoardServiceImpl implements TripBoardService {
    private final TripBoardMapper tripBoardMapper;
    private final MemberMapper memberMapper;

    @Override
    public List<TripBoardDto> getAllTripArticles(int pgno, int pageSize) {
    	int start = pgno * pageSize - pageSize;
    	int listsize = pageSize;
    	
        return tripBoardMapper.getAllTripArticles(start, listsize);
    }

    @Override
    public int insertTripArticle(TripBoardDto tripBoardDto) { return tripBoardMapper.insertTripArticle(tripBoardDto);}

    @Override
    public TripBoardDto getTripArticleByNumber(int articleNo) {
    	tripBoardMapper.updateTripHit(articleNo);
    	return tripBoardMapper.getTripArticleByNumber(articleNo);
    }
    
    @Override
    public int deleteTripArticle(int articleNo) { return tripBoardMapper.deleteTripArticle(articleNo);}
    
    @Override
    public int modifyTripArticle(TripBoardDto article) { return tripBoardMapper.modifyTripArticle(article);}
    
    @Override
    public List<TripBoardDto> getTripArticlesBySubject(String subject) {return tripBoardMapper.getTripArticlesBySubject(subject);}

	@Override
    public int getTripTotal() {
		return tripBoardMapper.getTotal();
	}

    @Override
    public List<TripRouteDto> getTripRouteByUserId(String userId) { return tripBoardMapper.getTripRouteByUserId(userId);}

    @Override
    public List<TripBoardRouteDetailDto> getRouteDetails(int planId) {return tripBoardMapper.getRouteDetails(planId);}

	@Override
    public MemberInfoDto getArticleUserInfo(String userId) throws SQLException {
		return memberMapper.getMemberInfoById(userId);
	}

    @Override
    public List<TripBoardDto> getTripArticlesById(String userId) {
        return tripBoardMapper.getTripArticlesById(userId);
    }
}
