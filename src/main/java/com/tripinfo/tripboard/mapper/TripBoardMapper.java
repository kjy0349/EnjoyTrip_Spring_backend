package com.tripinfo.tripboard.mapper;

import com.tripinfo.tripboard.dto.TripBoardDto;
import com.tripinfo.tripboard.dto.TripRouteDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TripBoardMapper {
    @Select("select * from trip_board order by article_no desc limit #{start}, #{listsize}")
    @Results(id = "tripBoardMap", value = {
            @Result(column = "article_no", property = "articleNo"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "subject", property = "subject"),
            @Result(column = "content", property = "content"),
            @Result(column = "hit", property = "hit"),
            @Result(column = "plan_id", property="planId"),
            @Result(column = "cost", property="cost"),
            @Result(column = "register_time", property = "registerTime")
    })
    List<TripBoardDto> getAllTripArticles(int start, int listsize);
    
    @Select("select count(*) from trip_board")
    int getTotal();

    @Select("select * from trip_board where subject like '%${subject}%' order by article_no desc")
    @ResultMap("tripBoardMap")
    List<TripBoardDto> getTripArticlesBySubject(String subject);

    @Insert("insert into trip_board (user_id, subject, content, plan_id, cost) values(#{userId}, #{subject}, #{content}, #{planId}, #{cost})")
    int insertTripArticle(TripBoardDto boardDto);


    @Select("select * from trip_route where user_id=#{userId}")
    @Results(id="tripRouteMap", value = {
            @Result(column = "plan_id", property = "planId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title")
    })
    List<TripRouteDto> getTripRouteByUserId(String userId);


    @Select("select * from trip_board where article_no=#{articleNo}")
    @ResultMap("tripBoardMap")
    TripBoardDto getTripArticleByNumber(int articleNo);
    
    @Delete("delete from trip_board where article_no = #{articleNo}")
    int deleteTripArticle(int articleNo);
    
    @Update("update trip_board set subject = #{subject}, content = #{content}, plan_id = #{planId}, cost = #{cost} where article_no = #{articleNo}")
    int modifyTripArticle(TripBoardDto article);
    
    @Update("update trip_board set hit = hit + 1 where article_no = #{articleNo}")
    int updateTripHit(int articleNo);

    @Select("select * from route_detail where plan_id = #{planId} order by place_date")
    int getRouteDetails(int planId);
}
