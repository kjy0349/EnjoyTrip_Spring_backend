package com.tripinfo.member.model.mapper;

import java.sql.SQLException;

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

@Mapper
public interface MemberMapper {
	@Select("select user_id from member where user_id = #{userId}")
//	@ResultType(String.class)
	String idCheck(String userId) throws SQLException;
	
	@Insert("insert into member (user_id, user_name, user_pass, email_id, email_domain, salt) "
			+ "values (#{userId}, #{userName}, #{userPass}, #{emailId}, #{emailDomain}, #{salt} )")
	@Options(useGeneratedKeys = true, keyProperty = "no")
	int joinMember(MemberDto memberDto) throws SQLException;
	
	
	@Select("select * from member where user_id = #{userId} and user_pass = #{userPass}")
	@Results(id = "basicMember" , value = {
			@Result(property = "userId", column = "user_id"),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "userPass", column = "user_pass"),
			@Result(property = "emailId", column = "email_id"),
			@Result(property = "emailDomain", column = "email_domain"),
			@Result(property = "salt", column = "salt")
	})
	MemberDto loginMember(String userId, String userPass) throws SQLException;
	
	@Update("update member set user_name = #{userName}, user_pass = #{userPass}, email_id = #{emailId}, email_domain = #{emailDomain} where user_id = #{userId}")
	int modify(MemberDto memberDto) throws SQLException;
	
	@Delete("delete from member where user_id = #{userId}")
	int delete(String userId) throws SQLException;
	
	@Select("select * from member where user_id = #{userId}")
	@ResultMap("basicMember")
	MemberDto SearchMemberById(String userId) throws SQLException;
	
	

}
