package com.dev.cinema.dao;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    MovieSession add(MovieSession session);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession update(MovieSession session);

    void deleteById(Long id);

    Optional<MovieSession> get(Long id);
}
