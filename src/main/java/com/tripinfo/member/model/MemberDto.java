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
public class MemberDto {
	private String userId;
	private String userName;
	private String userPass;
	private String joinDate;
	private String emailId;
	private String emailDomain;
	private String salt;
	private int no;
}
