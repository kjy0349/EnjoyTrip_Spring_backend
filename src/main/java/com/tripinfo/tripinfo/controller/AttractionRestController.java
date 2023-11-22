package com.tripinfo.tripinfo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripinfo.tripinfo.dto.AttractionInfoDto;
import com.tripinfo.tripinfo.dto.RouteDetailDto;
import com.tripinfo.tripinfo.dto.SidoDto;
import com.tripinfo.tripinfo.dto.TripRouteDto;
import com.tripinfo.tripinfo.service.AttractionServiceImpl;
import com.tripinfo.tripinfo.service.AttractionSidoServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/info")
public class AttractionRestController {
    private final AttractionServiceImpl service;
    private final AttractionSidoServiceImpl sidoService;

    @GetMapping("/sidoinfo")
    public ResponseEntity<Map<String, Object>> sidoInfo() throws SQLException {
        // sido 정보 mapper search 후 넣어주기.
        List<SidoDto> sido = sidoService.sidoList();
        if (sido != null) {
            return handleSuccess(sido);
        } else return handleError(sido);
    }

    @PostMapping("/insert")
    public ResponseEntity<Map<String, Object>> insertPlanInfo(@RequestBody Map<String, Object> map) {
    	// mapper로 routeDto 맞춰주기
    	ObjectMapper mapper = new ObjectMapper();
    	TripRouteDto routeDto= mapper.convertValue(map.get("route"), TripRouteDto.class);

//    	System.out.println(routeDto);
    	
    	// mapper로 리스트로 받아온 RouteDetailDto 맞춰주기
    	List<Object> list = (List<Object>) map.get("places");
    	List<RouteDetailDto> detailList = new ArrayList<>();
    	
    	for(Object object: list) {
    		detailList.add(mapper.convertValue(object, RouteDetailDto.class));
    	}
//    	for (RouteDetailDto dto : detailList) System.out.println(dto);

    	int result = service.insertPlanInfo(routeDto, detailList);
        if (result == 0) return handleError(result);
        else return handleSuccess(result);
    }

    @GetMapping("/attinfo/{contentId}/{sidoCode}/{title}")
    public ResponseEntity<Map<String, Object>> searchAtt(@PathVariable(required = false) String title,
                                                         @PathVariable(required = false) int contentId,
                                                         @PathVariable(required = false) int sidoCode) {
        if (title == null) title = "";
        List<AttractionInfoDto> attList = service.searchByTitle(title, sidoCode, contentId);
        if (attList != null) {
            return handleSuccess(attList);
        } else return handleError(attList);
    }

    @GetMapping("/randominfo")
    public ResponseEntity<Map<String, Object>> randomPick() {
        List<AttractionInfoDto> list = service.randomAttList();
        if (list != null) {
            return handleSuccess(list);
        } else return handleError(list);
    }


    private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> handleError(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
