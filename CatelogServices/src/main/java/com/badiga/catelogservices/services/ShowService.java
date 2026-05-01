package com.badiga.catelogservices.services;

import com.badiga.catelogservices.dtos.CreateShowRequest;
import com.badiga.catelogservices.dtos.ShowResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ShowService {
    ShowResponse createShow(CreateShowRequest request);
    Page<ShowResponse> getShows(Integer movieId, String city, String search, Pageable pageable);
}
