package com.tripinfo.tripinfo.service;

import com.tripinfo.tripinfo.dto.SidoDto;

import java.sql.SQLException;
import java.util.List;

public interface AttractionSidoService {
    List<SidoDto> sidoList() throws SQLException;
}
