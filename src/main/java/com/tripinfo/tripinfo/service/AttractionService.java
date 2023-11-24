package com.tripinfo.tripinfo.service;

import com.tripinfo.tripinfo.dto.AttractionInfoDto;
import com.tripinfo.tripinfo.dto.RouteDetailDto;
import com.tripinfo.tripinfo.dto.TripRouteDto;

import java.util.List;

public interface AttractionService {
    List<AttractionInfoDto> getAllAttractionList();

    // sidoCode가 0이 아니면 넣어주기.
    List<AttractionInfoDto> searchByTitle(String title, int sidoCode, int contentTypeId);

    List<AttractionInfoDto> randomAttList();

    int insertPlanInfo(TripRouteDto routeDto, List<RouteDetailDto> detailList);
}
