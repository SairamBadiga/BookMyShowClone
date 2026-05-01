package com.badiga.catelogservices.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter

public class CreateShowRequest {
    private Integer movieId;
    private Integer theatreId;
    private LocalDateTime startTime;
    private BigDecimal price;
}
