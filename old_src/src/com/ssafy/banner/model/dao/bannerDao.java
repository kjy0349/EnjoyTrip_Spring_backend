package com.ssafy.banner.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.banner.model.bannerDto;

public interface bannerDao {
	List<bannerDto> listSights(Connection con, String subject) throws SQLException;
}
