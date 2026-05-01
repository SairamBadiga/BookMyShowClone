package com.badiga.catelogservices.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movies")
@Getter @Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String language;

    private Integer duration;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Show> shows;

}
