package com.ssafy.banner.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.banner.model.bannerDto;

public interface bannerService {
	List<bannerDto> listSights( String subject) throws SQLException;
}
