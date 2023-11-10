package com.ssafy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.member.model.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	
	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;
	
	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String userId) throws SQLException {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(user_id) \n");
			loginMember.append("from member \n");
			loginMember.append("where user_id = ? ");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	
	
	// 여기에서 유저 패스워드 검사! 
	@Override
	public int joinMember(MemberDto memberDto) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into member (user_id, user_name, user_pass, salt) \n");
			sql.append("values (?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDto.getUserId());
			pstmt.setString(2, memberDto.getUserName());
			pstmt.setString(3, memberDto.getUserPwd());
			pstmt.setString(4, memberDto.getSalt());			
			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.printf("로그인: %s, %s\n", userId, userPwd);
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select * \n");
			loginMember.append("from member \n");
			loginMember.append("where user_id = ? and user_pass = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_pass"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public int modify(String userId, String userPwd) throws SQLException {
		int res = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update member set user_pass = ? where user_id = ?";
		
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			res = pstmt.executeUpdate();
		} finally {
			DBUtil.getInstance().close(pstmt, con);
		}
		System.out.printf("dao: %d\n", res);
		return res;
	}

	@Override
	public int delete(String userId) throws SQLException {
		int res = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member\r\n" + 
				"where user_id = ?";

		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			res = pstmt.executeUpdate();
		} finally {
			DBUtil.getInstance().close(pstmt, con);
		}
		
		System.out.println(res);
		return res;
	}

	@Override
	public MemberDto SearchMemberById(String userId) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select * \n");
			loginMember.append("from member \n");
			loginMember.append("where user_id = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_pass"));
				memberDto.setSalt(rs.getString("salt"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

}
