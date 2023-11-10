package com.ssafy.banner.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.banner.model.bannerDto;
import com.ssafy.util.DBUtil;

public class bannerDaoImpl implements bannerDao {
	private static bannerDaoImpl impl = new bannerDaoImpl();
	public static bannerDaoImpl getimpl() {
		return impl;
	}
	
	private bannerDaoImpl() {
		
	}

	@Override
	public List<bannerDto> listSights(Connection con, String subject) throws SQLException {
		List<bannerDto> list = new ArrayList<bannerDto>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "select * from attraction_description as a, attraction_info as b where a.content_id = b.content_id and (overview like ? or title like ? or addr1 like ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + subject + "%" );
			pstmt.setString(2, "%" + subject + "%" );
			pstmt.setString(3, "%" + subject + "%" );
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bannerDto dto = new bannerDto(rset.getString("overview"), rset.getString("title"), rset.getString("addr1"), rset.getInt("readcount"));
				list.add(dto);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		finally {
			DBUtil.getInstance().close(con, pstmt, rset);
		}
		
		return list;
	}
	
	
}
