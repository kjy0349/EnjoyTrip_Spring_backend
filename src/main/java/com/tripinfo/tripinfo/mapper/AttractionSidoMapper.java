package com.tripinfo.tripinfo.mapper;

import com.tripinfo.tripinfo.dto.SidoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttractionSidoMapper {

	@Select("select * from sido")
	public List<SidoDto> sidoList() throws SQLException;
}
