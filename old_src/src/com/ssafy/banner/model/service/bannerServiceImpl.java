package com.ssafy.banner.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.banner.model.bannerDto;
import com.ssafy.banner.model.dao.bannerDaoImpl;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.banner.model.bannerDto;
import com.ssafy.util.DBUtil;

public class bannerServiceImpl implements bannerService{
	private static bannerServiceImpl impl = new bannerServiceImpl();
	public static bannerServiceImpl getimpl() {
		return impl;
	}
	
	private bannerServiceImpl() {
		
	}

	@Override
	public List<bannerDto> listSights(String subject) throws SQLException {
		List<bannerDto> list = new ArrayList<bannerDto>();
		
		Connection con = null;
		
		try {
			con = DBUtil.getInstance().getConnection();
			con.setAutoCommit(false);
			list = bannerDaoImpl.getimpl().listSights(con, subject);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return list;
	}
}
