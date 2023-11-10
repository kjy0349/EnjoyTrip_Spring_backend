package com.ssafy.enjoytrip.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.SidoDto;
import com.ssafy.enjoytrip.model.dao.AttractionSidoDaoImpl;
import com.ssafy.util.DBUtil;

public class AttractionSidoServiceImpl implements AttractionSidoService{
	private AttractionSidoServiceImpl() {}
	private static AttractionSidoServiceImpl impl = new AttractionSidoServiceImpl();
	public static AttractionSidoServiceImpl getInstance() {
		return impl;
	}

	@Override
	public List<SidoDto> sidoList() throws SQLException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			return AttractionSidoDaoImpl.getInstance().sidoList(con);	
		} finally {
			DBUtil.getInstance().close(con);
		}
	}
}
