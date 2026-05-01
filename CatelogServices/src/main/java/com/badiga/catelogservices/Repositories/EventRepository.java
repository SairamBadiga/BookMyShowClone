package com.badiga.catelogservices.Repositories;

import com.badiga.catelogservices.entities.OutboxEvent;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<OutboxEvent, Long> {
    List<OutboxEvent> findAll();
}
