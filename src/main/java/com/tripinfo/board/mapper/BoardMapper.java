package com.tripinfo.board.mapper;

import com.tripinfo.board.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select * from board order by article_no desc limit #{start}, #{listsize}")
    @Results(id = "boardMap", value = {
            @Result(column = "article_no", property = "articleNo"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "subject", property = "subject"),
            @Result(column = "content", property = "content"),
            @Result(column = "hit", property = "hit"),
            @Result(column = "register_time", property = "registerTime")
    })
    List<BoardDto> getAllArticles(int start, int listsize);
    
    @Select("select count(*) from board")
    int getTotal();

    @Select("select * from board where subject like '%${subject}%' order by article_no desc")
    @ResultMap("boardMap")
    List<BoardDto> getArticlesBySubject(String subject);

    @Insert("insert into board (user_id, subject, content) values(#{userId}, #{subject}, #{content})")
    int insertArticle(BoardDto boardDto);

    @Select("select * from board where article_no=#{articleNo}")
    @ResultMap("boardMap")
    BoardDto getArticleByNumber(int articleNo);
    
    @Delete("delete from board where article_no = #{articleNo}")
    int deleteArticle(int articleNo);
    
    @Update("update board set subject = #{subject}, content = #{content} where article_no = #{articleNo}")
    int modifyArticle(BoardDto article);
    
    @Update("update board set hit = hit + 1 where article_no = #{articleNo}")
    int updateHit(int articleNo);
}
