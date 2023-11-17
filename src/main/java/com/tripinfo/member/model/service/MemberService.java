package com.tripinfo.member.model.service;

import java.sql.SQLException;

import com.tripinfo.member.model.FileInfo;
import com.tripinfo.member.model.MemberDto;

public interface MemberService {
	String idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto, FileInfo fileInfo) throws Exception;
	MemberDto login(String userId, String userPass) throws Exception;
	int modify(MemberDto memberDto) throws Exception;
	int delete(String userId) throws Exception;
	MemberDto SearchMemberById(String userId) throws SQLException;
    void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
}
