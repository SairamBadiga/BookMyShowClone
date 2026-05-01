package com.badiga.catelogservices.Repositories;

import com.badiga.catelogservices.entities.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    Page<Show> findByMovieIdAndTheatre_City(Integer movieId, String city);

}
