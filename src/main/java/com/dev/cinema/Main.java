package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) throws AuthenticationException {
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
        movieSession.setShowTime(LocalDateTime.of(2021,2,4,23,0));
        movieSessionSecond.setShowTime(LocalDateTime.of(2021,2,4,18,0));

        movieService.add(movie);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.add(cinemaHallSecond);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSessionSecond);

        movieService.getAll().forEach(System.out::println);
        cinemaHallService.getAll().forEach(System.out::println);
        System.out.println("findAvailableSessions");
        movieSessionService.findAvailableSessions(movie.getId(),
                LocalDate.now()).forEach(System.out::println);

        User user = authenticationService.register("test@gmail.com", "1a2b3c");
        System.out.println(user);

        User checkUser = authenticationService.login("test@gmail.com", "1a2b3c");
        System.out.println(checkUser);

        ShoppingCart shoppingCartByUser = shoppingCartService.getByUser(user);
        System.out.println(shoppingCartByUser);

        shoppingCartService.addSession(movieSession, user);
        shoppingCartService.addSession(movieSessionSecond, user);
        ShoppingCart userCartWithTickets = shoppingCartService.getByUser(user);
        System.out.println("userCartWithTickets : " + userCartWithTickets);

        Order order = orderService.completeOrder(userCartWithTickets);
        System.out.println("order : " + order);
        System.out.println(userCartWithTickets);
        List<Order> orderList = orderService.getOrdersHistory(user);
        System.out.println(orderList);
        System.out.println(orderList.size());
    }
}
