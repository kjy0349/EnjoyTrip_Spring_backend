package com.tripinfo.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tripinfo.tripboard.dto.TripBoardDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.tripinfo.member.model.FileInfo;
import com.tripinfo.member.model.MemberDto;
import com.tripinfo.member.model.mapper.MemberMapper;

@Service
@AllArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

	private final MemberMapper mapper;
	@Override
	public String idCheck(String userId) throws Exception {
		return mapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto, FileInfo fileInfo) throws Exception {
		String encrypted = BCrypt.hashpw(memberDto.getUserPass(), BCrypt.gensalt());
		memberDto.setUserPass(encrypted);
		int result = mapper.joinMember(memberDto);
		if(result > 0) {
			fileInfo.setUserId(memberDto.getUserId());
			result = mapper.insertFileInfo(fileInfo);
		} else {
			System.out.println("insert error");
		}
		return result;
	}

	@Override
	public MemberDto login(String userId, String userPass) throws Exception {
		// userId로 멤버를 가져와서 userPW를 해시 돌린 것과 비교 
		MemberDto temp = SearchMemberById(userId);
		if(temp == null) {
			// 아이디도 없는거
			return null;
		}
		else {
			// 로그인 성공
			if(BCrypt.checkpw(userPass, temp.getUserPass())) {
				return temp;
			}
			else {
				// 비번 틀림
				return null;
			}
		}
	}

	@Override
	public int modify(MemberDto memberDto) throws Exception { // 추가 수정 가능
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
		mapper.deleteRefreshToken(map);
	}

	@Override
	public FileInfo getProfilePic(String userId) {
		return mapper.getProfilePic(userId);
	}

	@Override
	public List<TripBoardDto> getMyTripList(String userId) { return mapper.getMyTripList(userId);}
}
