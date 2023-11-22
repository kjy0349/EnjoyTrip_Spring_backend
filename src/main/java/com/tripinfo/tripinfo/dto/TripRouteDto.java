package com.tripinfo.tripinfo.dto;

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
