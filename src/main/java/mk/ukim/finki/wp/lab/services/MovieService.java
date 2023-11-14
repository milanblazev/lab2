package mk.ukim.finki.wp.lab.services;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);

    void increaseTicketOrders(String title, int amount);
    void deleteById(long id);

    Optional<Movie> findById(Long id);

    void save(String movieNewTitle, String movieNewSummary, String movieNewRating, Production newProduction);

    void edit(Long id, String movieNewTitle, String movieNewSummary, String movieNewRating, Production newProduction);
}

