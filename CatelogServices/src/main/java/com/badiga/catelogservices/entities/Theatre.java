package com.badiga.catelogservices.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "theatres")
@Getter @Setter
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String city;

    @OneToMany(mappedBy = "theatre", fetch = FetchType.LAZY)
    private List<Show> shows;
}
