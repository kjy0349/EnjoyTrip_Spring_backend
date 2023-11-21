package com.tripinfo.tripcomment.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TripCommentDto {
	private int commentNo;
	private String userId;
	private int articleNo;
	private String content;
	private String registerTime;
	private boolean chosen;
}
