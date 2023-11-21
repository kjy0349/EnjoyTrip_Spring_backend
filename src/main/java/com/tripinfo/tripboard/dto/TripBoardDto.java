package com.tripinfo.tripboard.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TripBoardDto {

	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;
	private int planId;
	private int cost;
	private String registerTime;
}
