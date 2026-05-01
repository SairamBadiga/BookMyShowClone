package com.badiga.catelogservices.mappers;

import com.badiga.catelogservices.dtos.CreateShowRequest;
import com.badiga.catelogservices.dtos.ShowResponse;
import com.badiga.catelogservices.entities.Movie;
import com.badiga.catelogservices.entities.Show;
import com.badiga.catelogservices.entities.Theatre;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {
    public Show toEntity(CreateShowRequest req, Movie movie, Theatre theatre) {
        Show show = new Show();
        show.setMovie(movie);
        show.setTheatre(theatre);
        show.setStartTime(req.getStartTime());
        show.setPrice(req.getPrice());
        return show;
    }

    public ShowResponse toResponse(Show show) {
        return ShowResponse.builder()
                .id(show.getId())
                .movieName(show.getMovie().getTitle())
                .theatre(show.getTheatre().getName())
                .city(show.getTheatre().getCity())
                .startTime(show.getStartTime())
                .build();
    }
}
