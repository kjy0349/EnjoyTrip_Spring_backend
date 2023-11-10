package com.tripinfo.tripinfo.mapper;

import com.tripinfo.tripinfo.dto.AttractionInfoDto;
import com.tripinfo.tripinfo.sql.AttractionSQL;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttractionMapper {

	@Select("select * from attraction_info")
	@Results(id = "attractionMap", value = {
			@Result(property = "contentId", column ="content_id"),
			@Result(property = "contentTypeId", column ="content_type_id"),
			@Result(property = "title", column ="title"),
			@Result(property = "addr1", column ="addr1"),
			@Result(property = "addr2", column ="addr2"),
			@Result(property = "zipcode", column ="zipcode"),
			@Result(property = "tel", column ="tel"),
			@Result(property = "firstImage", column ="first_image"),
			@Result(property = "firstImage2", column ="first_image2"),
			@Result(property = "readcount", column ="readcount"),
			@Result(property = "sidoCode", column ="sido_code"),
			@Result(property = "gugunCode", column ="gugun_code"),
			@Result(property = "latitude", column ="latitude"),
			@Result(property = "longitude", column ="longitude"),
			@Result(property = "mlevel", column ="mlevel"),
	})
	List<AttractionInfoDto> getAllAttractionList();

	// sidoCode가 0이 아니면 넣어주기.
	@ResultMap("attractionMap")
	@SelectProvider(type = AttractionSQL.class, method = "searchByTitle")
	List<AttractionInfoDto> searchByTitle(String title, int sidoCode, int contentTypeId);

	@ResultMap("attractionMap")
	@Select("select * from attraction_info order by rand() limit 6")
	List<AttractionInfoDto> randomAttList();
}
