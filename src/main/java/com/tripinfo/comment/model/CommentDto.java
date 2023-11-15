package com.tripinfo.comment.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentDto {
	private int commentNo;
	private String userId;
	private int articleNo;
	private String content;
	private String registerTime;
}
