package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Cinema hall BLUE");

        CinemaHall cinemaHallSecond = new CinemaHall();
        cinemaHallSecond.setCapacity(50);
        cinemaHallSecond.setDescription("Cinema hall RED");

        MovieSession movieSession = new MovieSession();
        MovieSession movieSessionSecond = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSessionSecond.setMovie(movie);
        movieSessionSecond.setCinemaHall(cinemaHallSecond);
        movieSession.setShowTime(LocalDateTime.of(2021,2,1,23,0));
        movieSessionSecond.setShowTime(LocalDateTime.of(2021,2,1,18,0));

        movieService.add(movie);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.add(cinemaHallSecond);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSessionSecond);

        movieService.getAll().forEach(System.out::println);
        cinemaHallService.getAll().forEach(System.out::println);
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(System.out::println);
    }
}
