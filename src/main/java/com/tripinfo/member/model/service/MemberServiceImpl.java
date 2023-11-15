package com.tripinfo.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tripinfo.crypto.CryptoPW;
import com.tripinfo.member.model.MemberDto;
import com.tripinfo.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper mapper;

	@Override
	public String idCheck(String userId) throws Exception {
		return mapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		// 여기서 패스워드 + salt 를 해시 돌려서 다이제스트와 salt를 DB에 같이 저장 
		String Salt = CryptoPW.getCryptoPW().getSALT();
		String HashDigest = CryptoPW.getCryptoPW().Hashing(memberDto.getUserPass().getBytes(), Salt);
		
		memberDto.setUserPass(HashDigest);
		memberDto.setSalt(Salt);
		
		
		return mapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPass) throws Exception {
		// userId로 멤버를 가져와서 userPW를 해시 돌린 것과 비교 
		MemberDto temp = SearchMemberById(userId);
		if(temp == null) {
			// 아이디도 없는거
			return null;
		}
		else {
			// 로그인 성공
			if(temp.getUserPass().equals(CryptoPW.getCryptoPW().Hashing(userPass.getBytes(), temp.getSalt()))) {
				return mapper.loginMember(userId, CryptoPW.getCryptoPW().Hashing(userPass.getBytes(), temp.getSalt()));	
			}
			else {
				// 비번 틀림
				System.out.println("비번 틀림");
				return null;
			}
		}
		
		
	}

	@Override
	public int modify(MemberDto memberDto) throws Exception {
		
		MemberDto temp = SearchMemberById(memberDto.getUserId());
		memberDto.setUserPass(CryptoPW.getCryptoPW().Hashing(memberDto.getUserPass().getBytes(), temp.getSalt()));
		
		return mapper.modify(memberDto);
	}

	@Override
	public int delete(String userId) throws Exception {
		return mapper.delete(userId);
	}

	@Override
	public MemberDto SearchMemberById(String userId) throws SQLException {
		return mapper.SearchMemberById(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return mapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

}
