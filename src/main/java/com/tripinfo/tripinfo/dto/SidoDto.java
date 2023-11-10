package com.tripinfo.tripinfo.dto;

import lombok.Data;

@Data
public class SidoDto {

	private int sidoCode;
	private String sidoName;
	
	public SidoDto(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
}
