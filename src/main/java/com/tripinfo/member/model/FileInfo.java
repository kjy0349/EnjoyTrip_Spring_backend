package com.tripinfo.member.model;

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
public class FileInfo {
	
	private int no;
	private String userId;
	private String originalFile;
	private String saveFile;
	private String saveFolder;
	
}
