package com.badiga.catelogservices.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class ShowResponse {
    private Long id;
    private String movieName;
    private String theatre;
    private String city;
    private LocalDateTime startTime;
}
