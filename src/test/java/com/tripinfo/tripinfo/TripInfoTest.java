package com.tripinfo.tripinfo;

import com.tripinfo.tripinfo.dto.AttractionInfoDto;
import com.tripinfo.tripinfo.mapper.AttractionMapper;
import com.tripinfo.tripinfo.mapper.AttractionSidoMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class TripInfoTest {
    @Autowired
    AttractionMapper mapper;

    @Autowired
    AttractionSidoMapper sidoMapper;

    @Test
    public void getAllTest() {
        assertThat(mapper.getAllAttractionList().size()).isEqualTo(36181);
    }

    @Test
    public void searchTest() {
        List<AttractionInfoDto> list = mapper.searchByTitle("서울", 1, 12);
        log.debug("{}", list.size());
        list = mapper.searchByTitle("서울", 1, 0);
        assertThat(list.size()).isEqualTo(399);
    }

    @Test
    public void randomTest() {
        List<AttractionInfoDto> list = mapper.randomAttList();
        for (AttractionInfoDto dto : list) log.debug("{}", dto);
    }

    @Test
    public void sidoListTest() throws SQLException {
        assertThat(sidoMapper.sidoList().size()).isEqualTo(17);
    }
}
