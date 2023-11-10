package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.model.SidoDto;
import com.ssafy.util.DBUtil;

public class AttractionSidoDaoImpl implements AttractionSidoDao{
	private AttractionSidoDaoImpl() {}
	private static AttractionSidoDaoImpl impl = new AttractionSidoDaoImpl();
	public static AttractionSidoDaoImpl getInstance() {
		return impl;
	}

	@Override
	public List<SidoDto> sidoList(Connection con) throws SQLException {
		List<SidoDto> list = new ArrayList<>();
		
		String sql = "SELECT * from sido";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int sidoCode = rset.getInt("sido_code");
				String sidoName = rset.getString("sido_name");
				
				list.add(new SidoDto(sidoCode, sidoName));
			}
			
			return list;
		} finally {
			DBUtil.getInstance().close(rset, pstmt);
		}
	}

}
