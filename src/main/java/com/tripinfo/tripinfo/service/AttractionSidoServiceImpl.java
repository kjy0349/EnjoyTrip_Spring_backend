package com.tripinfo.tripinfo.service;

import com.tripinfo.tripinfo.dto.SidoDto;
import com.tripinfo.tripinfo.mapper.AttractionSidoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class AttractionSidoServiceImpl implements AttractionSidoService {
    private final AttractionSidoMapper sidoMapper;

    @Override
    public List<SidoDto> sidoList() throws SQLException {
        return sidoMapper.sidoList();
    }
}
