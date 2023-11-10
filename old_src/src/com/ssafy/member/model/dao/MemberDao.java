package com.ssafy.member.model.dao;

import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	int joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	int modify(String userId, String userPwd) throws SQLException;
	int delete(String userId) throws SQLException;
	MemberDto SearchMemberById(String userId) throws SQLException;
	
}
