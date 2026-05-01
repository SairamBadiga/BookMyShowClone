package com.badiga.catelogservices.events;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ShowCreatedEvent {
    Integer showId;
    Integer movieId;
    String city;
}
