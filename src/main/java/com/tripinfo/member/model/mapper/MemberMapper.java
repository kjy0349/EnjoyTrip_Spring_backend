package com.tripinfo.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tripinfo.tripboard.dto.TripBoardDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tripinfo.member.model.MemberDto;
import com.tripinfo.member.MemberInfoDto;
import com.tripinfo.member.model.FileInfo;

@Mapper
public interface MemberMapper {
	@Select("select user_id from member where user_id = #{userId}")
//	@ResultType(String.class)
	String idCheck(String userId) throws SQLException;
	
	@Insert("insert into member (user_id, user_name, user_pass, email_id, email_domain, mbti, gender, age) "
			+ "values (#{userId}, #{userName}, #{userPass}, #{emailId}, #{emailDomain}, #{mbti}, #{gender}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "no")
	int joinMember(MemberDto memberDto) throws SQLException;
	
	@Insert("insert into file_info (user_id, save_folder, original_file, save_file) values (#{userId}, #{saveFolder}, #{originalFile}, #{saveFile})")
	int insertFileInfo(FileInfo file);

	@Update("update member set user_name = #{userName}, user_pass = #{userPass}, email_id = #{emailId}, email_domain = #{emailDomain} where user_id = #{userId}")
	int modify(MemberDto memberDto) throws SQLException;
	
	@Delete("delete from member where user_id = #{userId}")
	int delete(String userId) throws SQLException;
	
	@Select("select * from member where user_id = #{userId}")
	@Results(id = "basicMember" , value = {
			@Result(property = "userId", column = "user_id"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "userPass", column = "user_pass"),
			@Result(property = "emailId", column = "email_id"),
			@Result(property = "joinDate", column = "join_date"),
			@Result(property = "refreshToken", column = "token"),
			@Result(property = "emailDomain", column = "email_domain"),
	})
	MemberDto SearchMemberById(String userId) throws SQLException;

	@Select("select * from file_info where user_id = #{userId}")
	@Results(id = "basicFileInfo", value = {
			@Result(property = "userId", column = "user_id"),
			@Result(property = "no", column = "no"),
			@Result(property = "originalFile", column = "original_file"),
			@Result(property = "saveFile", column = "save_file"),
			@Result(property = "saveFolder", column = "save_folder"),
	})
	FileInfo getProfilePic(String userId);

	@Update("update member set token = #{token} where user_id = #{userId}")
	void saveRefreshToken(Map<String, String> map) throws SQLException;

	@Select("select token from member where user_id = #{userId}")
	String getRefreshToken(String userId) throws SQLException;

	@Update("update member set token = #{token} where user_id = #{userId}")
	void deleteRefreshToken(Map<String, String> map) throws SQLException;

	@Select("select user_name, join_date, star, star_count, mbti, gender, age, hit, content, profile_no from member where user_id = #{userId}")
	@Results(id = "memberInfo" , value = {
			@Result(property = "userName", column = "user_name"),
			@Result(property = "joinDate", column = "join_date"),
			@Result(property = "star", column = "star"),
			@Result(property = "starCount", column = "star_count"),
			@Result(property = "mbti", column = "mbti"),
			@Result(property = "gender", column = "gender"),
			@Result(property = "age", column = "age"),
			@Result(property = "hit", column = "hit"),
			@Result(property = "content", column = "content"),
			@Result(property = "profileNo", column = "profile_no")
	})
	MemberInfoDto getMemberInfoById(String userId) throws SQLException;

	@Select("select *\n" +
			"from trip_board\n" +
			"where article_no = (select tc.article_no\n" +
			"from trip_comment tc join trip_board tb\n" +
			"on tc.article_no = tb.article_no\n" +
			"where tc.user_id = #{userId} and tc.chosen = 1)")
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
	List<TripBoardDto> getMyTripList(String userId);
}
