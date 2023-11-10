package com.ssafy.crypto;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.print.DocFlavor.BYTE_ARRAY;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

public class CryptoPW {

	private static final int SALT_SIZE = 16;
	
	// 싱글톤 해야해?
	private static CryptoPW cryptoPass = new CryptoPW();
	
	private CryptoPW() {
	}
	
	public static CryptoPW getCryptoPW() {
		return cryptoPass;
	}
	

	

	// 비밀번호 해싱
	public String Hashing (byte[] password, String Salt) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-256");	// sha 256 사용
		
		// key - stretching
		for(int i=0; i < 10000; i++) {
			String temp = Byte_to_String(password) + Salt; // 패스워드와 salt를 합쳐 새로운 문자열 생성
			md.update(temp.getBytes());	// temp의 문자열을 해싱하여 md에 저장
			password = md.digest();	// md 객체의 digest를 얻어 password 갱신
		}
		return Byte_to_String(password);
	}

	// SALT 값 생성  
	public String getSALT() throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rnd.nextBytes(temp);
		
		return Byte_to_String(temp);
	}
	
	// 바이트 값을 16진수로 변경해준다
	public String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for (byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
}
