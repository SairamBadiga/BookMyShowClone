package com.badiga.catelogservices.Repositories;

import com.badiga.catelogservices.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
}
