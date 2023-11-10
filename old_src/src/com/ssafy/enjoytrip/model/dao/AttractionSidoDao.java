package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.SidoDto;

public interface AttractionSidoDao {
	List<SidoDto> sidoList(Connection con) throws SQLException;
}
