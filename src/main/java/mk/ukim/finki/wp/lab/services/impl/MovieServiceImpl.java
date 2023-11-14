package mk.ukim.finki.wp.lab.services.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.MovieRepository;
import mk.ukim.finki.wp.lab.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public void increaseTicketOrders(String title, int amount){
        movieRepository.increaseTicketOrders(title,amount);
    }

    @Override
    public void deleteById(long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void save(String movieNewTitle, String movieNewSummary, String movieNewRating, Production newProduction) {
        movieRepository.save(movieNewTitle, movieNewSummary, movieNewRating, newProduction);
    }

    @Override
    public void edit(Long id, String movieNewTitle, String movieNewSummary, String movieNewRating, Production newProduction) {
        movieRepository.edit(id, movieNewTitle, movieNewSummary, movieNewRating, newProduction);
    }
}
