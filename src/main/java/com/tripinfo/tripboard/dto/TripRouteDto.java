package com.tripinfo.tripboard.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TripRouteDto {
    private int planId;
    private String userId;
    private String title;
}
