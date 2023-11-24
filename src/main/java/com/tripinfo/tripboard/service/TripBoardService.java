package com.tripinfo.tripboard.service;

import com.tripinfo.member.MemberInfoDto;
import com.tripinfo.tripboard.dto.TripBoardDto;
import com.tripinfo.tripboard.dto.TripBoardRouteDetailDto;
import com.tripinfo.tripboard.dto.TripRouteDto;

import java.sql.SQLException;
import java.util.List;

public interface TripBoardService {
    List<TripBoardDto> getAllTripArticles(int pgno, int pageSize);

    int insertTripArticle(TripBoardDto tripBoardDto);

    TripBoardDto getTripArticleByNumber(int articleNo);

    int deleteTripArticle(int articleNo);

    int modifyTripArticle(TripBoardDto article);

    List<TripBoardDto> getTripArticlesBySubject(String subject);

    int getTripTotal();

    List<TripRouteDto> getTripRouteByUserId(String userId);

    List<TripBoardRouteDetailDto> getRouteDetails(int planId);

    MemberInfoDto getArticleUserInfo(String userId) throws SQLException;

    List<TripBoardDto> getTripArticlesById(String userId);
}
