package com.badiga.catelogservices.services;


import com.badiga.catelogservices.Repositories.EventRepository;
import com.badiga.catelogservices.Repositories.MovieRepository;
import com.badiga.catelogservices.Repositories.ShowRepository;
import com.badiga.catelogservices.Repositories.TheatreRepository;
import com.badiga.catelogservices.dtos.CreateShowRequest;
import com.badiga.catelogservices.dtos.ShowResponse;
import com.badiga.catelogservices.entities.*;
import com.badiga.catelogservices.events.ShowCreatedEvent;
import com.badiga.catelogservices.exceptions.NotFoundException;
import com.badiga.catelogservices.mappers.ShowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;
    private final EventRepository eventRepository;
    private final ShowMapper mapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public ShowResponse createShow(CreateShowRequest request) {

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found"));


        Theatre theatre = theatreRepository.findById(request.getTheatreId())
                .orElseThrow(() -> new NotFoundException("Theatre not found"));

        Show show = mapper.toEntity(request, movie, theatre);

        Show saved = showRepository.save(show);

        // 🔥 Publish event (CQRS)
        ShowCreatedEvent event = new ShowCreatedEvent();
        event.setShowId(saved.getId());
        event.setMovieId(movie.getId());
        event.setCity(theatre.getCity());

        kafkaTemplate.send("show-created", event);

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShowResponse> getShows(Integer movieId, String city, String search, Pageable pageable) {
        return showRepository.findByMovieIdAndTheatre_City(movieId, city)
                .map(mapper::toResponse);

    }
    @Scheduled(fixedDelay = 5000)
    public void publish(){
        List<OutboxEvent> events = eventRepository.findAll();
        for(OutboxEvent event : events){
            try{
                event.setStatus(Status.PROCESSING);
                eventRepository.save(event);
                kafkaTemplate.send("show-created", event.getPayload());
                event.setStatus(Status.SENT);

            }
            catch(Exception e){
                event.setRetryCount(event.getRetryCount() + 1);
                if (event.getRetryCount() > 5) {
                    event.setStatus(Status.FAILED);
                } else {
                    event.setStatus(Status.NEW);
                }
            }
            eventRepository.save(event);

        }

    }

}
