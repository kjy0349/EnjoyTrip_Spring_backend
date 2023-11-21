package com.tripinfo.tripcomment.model.mapper;

import com.tripinfo.tripcomment.model.TripCommentDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TripCommentMapper {

//	- (GET)getCommentByNo(@PathVariable("articleno") int articleNo) : articleNo로 CommentList
//	- (POST)writeComment(@RequestBody CommentDto comment) : Comment 작성
//	- (DELETE)deleteComment(@PathVariable("commentno") int commentNo) : commentNo로 comment삭제 처리
	
	@Select("select * from trip_comment where article_no = #{articleNo}")
	@Results(id = "tripCommentMap", value = {
			@Result(column = "comment_no", property = "commentNo"),
			@Result(column = "user_id", property = "userId"),
            @Result(column = "article_no", property = "articleNo"),
            @Result(column = "content", property = "content"),
            @Result(column = "register_time", property = "registerTime"),
			@Result(column = "chosen", property = "chosen")
    })
	List<TripCommentDto> getCommentByNo(int articleNo);
	
	@Insert("insert into trip_comment ( user_id, article_no, content) values (#{userId}, #{articleNo}, #{content})")
	int writeComment(TripCommentDto tripCommentDto);
	
	@Delete("delete from trip_comment where comment_no = #{tripCommentNo}")
	int deleteComment(int tripCommentNo);

	@Update("update trip_comment set chosen = chosen^1 where comment_no=#{tripCommentNo}")
	int updateSelectedStatus(int tripCommentNo);
	
}
