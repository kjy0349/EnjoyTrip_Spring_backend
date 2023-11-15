package com.tripinfo.comment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.tripinfo.comment.model.CommentDto;

@Mapper
public interface CommentMapper {

//	- (GET)getCommentByNo(@PathVariable("articleno") int articleNo) : articleNo로 CommentList
//	- (POST)writeComment(@RequestBody CommentDto comment) : Comment 작성
//	- (DELETE)deleteComment(@PathVariable("commentno") int commentNo) : commentNo로 comment삭제 처리
	
	@Select("select * from comment where article_no = #{articleNo}")
	@Results(id = "commentMap", value = {
			@Result(column = "comment_no", property = "commentNo"),
			@Result(column = "user_id", property = "userId"),
            @Result(column = "article_no", property = "articleNo"),
            @Result(column = "content", property = "content"),
            @Result(column = "register_time", property = "registerTime")
    })
	List<CommentDto> getCommentByNo(int articleNo);
	
	@Insert("insert into comment ( user_id, article_no, content) values (#{userId}, #{articleNo}, #{content})")
	int writeComment(CommentDto comment);
	
	@Delete("delete from comment where comment_no = #{commentNo}")
	int deleteComment(int commentNo);
	
}
