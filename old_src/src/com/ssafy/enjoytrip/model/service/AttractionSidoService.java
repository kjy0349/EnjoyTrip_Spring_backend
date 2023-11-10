package com.ssafy.enjoytrip.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.SidoDto;

public interface AttractionSidoService {
	List<SidoDto> sidoList() throws SQLException;
}
