package com.ssafy.member.model.service;

import java.sql.SQLException;

import com.ssafy.crypto.CryptoPW;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberDao.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		
		// 여기서 패스워드 + salt 를 해시 돌려서 다이제스트와 salt를 DB에 같이 저장 
		String Salt = CryptoPW.getCryptoPW().getSALT();
		String HashDigest = CryptoPW.getCryptoPW().Hashing(memberDto.getUserPwd().getBytes(), Salt);
		
		memberDto.setUserPwd(HashDigest);
		memberDto.setSalt(Salt);
		
		
		return memberDao.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		
		// userId로 멤버를 가져와서 userPW를 해시 돌린 것과 비교 
		MemberDto temp = SearchMemberById(userId);
		
		
		// 로그인 성공
		if(temp.getUserPwd().equals(CryptoPW.getCryptoPW().Hashing(userPwd.getBytes(), temp.getSalt()))) {
			return memberDao.loginMember(userId, CryptoPW.getCryptoPW().Hashing(userPwd.getBytes(), temp.getSalt()));	
		}
		
		// 로그인 실패 
		return null;		
	}

	@Override
	public int modify(String userId, String userPwd) throws Exception {
		
		MemberDto temp = SearchMemberById(userId);
		userPwd = CryptoPW.getCryptoPW().Hashing(userPwd.getBytes(), temp.getSalt());
		
		
		return memberDao.modify(userId, userPwd);
	}

	@Override
	public int delete(String userId) throws Exception {
		return memberDao.delete(userId);
	}

	@Override
	public MemberDto SearchMemberById(String userId) throws SQLException {
		return memberDao.SearchMemberById(userId);
	}

}
