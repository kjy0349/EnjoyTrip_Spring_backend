package com.tripinfo.tripinfo.dto;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RouteDetailDto {
    private int no;
    private int planId;
    private int contentId;
    private Date placeDate;
}
