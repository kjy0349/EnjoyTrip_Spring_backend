package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.util.List;

import com.ssafy.enjoytrip.model.AttractionInfoDto;

public interface AttractionDao {

	List<AttractionInfoDto> attractionList(Connection con, AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(Connection con, String title, int sidoCode);
	List<AttractionInfoDto> randomAttList(Connection con);
}
