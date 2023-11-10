package com.ssafy.enjoytrip.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.model.AttractionInfoDto;
import com.ssafy.enjoytrip.model.dao.AttractionDaoImpl;
import com.ssafy.util.DBUtil;

public class AttractionServiceImpl implements AttractionService{
	// 싱글톤 작업
	private static AttractionServiceImpl AttractionServiceImpl = new AttractionServiceImpl();
	private AttractionServiceImpl() {};
	public static AttractionServiceImpl getAttractionService() {
		return AttractionServiceImpl;
	}
	
	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) throws SQLException{
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			return AttractionDaoImpl.getAttractionDao().attractionList(con, attractionInfoDto);			
		} finally {
			DBUtil.getInstance().close(con);
		}
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title, int sidoCode) throws SQLException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			return AttractionDaoImpl.getAttractionDao().searchByTitle(con, title, sidoCode);
		}finally {
			DBUtil.getInstance().close(con);
		}
	}
	@Override
	public List<AttractionInfoDto> randomAttList() throws SQLException{
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			return AttractionDaoImpl.getAttractionDao().randomAttList(con);
		}finally {
			DBUtil.getInstance().close(con);
		}
	}

}
