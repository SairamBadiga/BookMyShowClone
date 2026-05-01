package com.badiga.catelogservices.controllers;

import com.badiga.catelogservices.dtos.CreateShowRequest;
import com.badiga.catelogservices.dtos.ShowResponse;
import com.badiga.catelogservices.services.ShowService;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService service;

    @PostMapping
    public ResponseEntity<ShowResponse> create(@RequestBody CreateShowRequest request) {
        return ResponseEntity.ok(service.createShow(request));
    }

    @GetMapping
    public Page<ShowResponse> getShows(
            @RequestParam(required = false) Integer movieId,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String search,
            Pageable pageable){
        return service.getShows(movieId, city, search, pageable);

    }

}
