package com.tripinfo.tripinfo.dto;

import com.tripinfo.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
public class TripRouteDto {
	private int planId; 
	private String userId;
	private String title;

}
