package com.tripinfo.member;

import org.springframework.web.multipart.MultipartFile;

import com.tripinfo.member.model.MemberDto;

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
public class MemberInfoDto {
    private String userName;
    private String joinDate;
    private MultipartFile imgSrc;
    private float star;
    private int starCount;
    private String mbti;
    private String gender;
    private int age;
    private int hit;
    private String content;
    private int profileNo;

}
