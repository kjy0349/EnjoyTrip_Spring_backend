package com.tripinfo.tripinfo.dto;

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
    private String placeDate;
}
