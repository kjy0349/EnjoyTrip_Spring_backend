package com.tripinfo.tripinfo.service;

import com.tripinfo.tripinfo.dto.AttractionInfoDto;
import com.tripinfo.tripinfo.mapper.AttractionMapper;
import com.tripinfo.tripinfo.sql.AttractionSQL;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttractionServiceImpl {
    private final AttractionMapper mapper;

    public List<AttractionInfoDto> getAllAttractionList() {
        return mapper.getAllAttractionList();
    }

    // sidoCode가 0이 아니면 넣어주기.
    public List<AttractionInfoDto> searchByTitle(String title, int sidoCode, int contentTypeId) {
        return mapper.searchByTitle(title, sidoCode, contentTypeId);
    }

    public List<AttractionInfoDto> randomAttList() {
        return mapper.randomAttList();
    }
}
