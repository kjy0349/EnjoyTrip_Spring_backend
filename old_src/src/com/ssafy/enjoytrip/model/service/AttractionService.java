package com.ssafy.enjoytrip.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.AttractionInfoDto;

public interface AttractionService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) throws SQLException;
 
	List<AttractionInfoDto> searchByTitle(String title, int sidoCode) throws SQLException;
	public List<AttractionInfoDto> randomAttList() throws SQLException;
}
