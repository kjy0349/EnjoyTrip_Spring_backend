package com.tripinfo.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberInfoDto {
    private String userName;
    private String joinDate;
    private MultipartFile imgSrc;
    private float star;
    private int star_count;
    private String mbti;
    private String gender;
    private int age;
    private int hit;
    private String content;
    private int profile_no;

}
