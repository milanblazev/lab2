package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.services.MovieService;
import mk.ukim.finki.wp.lab.services.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }
    @GetMapping()
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.listAll();
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "movies");
        return "listMovies";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = (Movie)this.movieService.findById(id).get();
            List<Production> productions = productionService.findAll().stream().filter(r -> !r.getName().equals(movie.getProduction().getName())).collect(Collectors.toList());
            model.addAttribute("movieTitle", movie.getTitle());
            model.addAttribute("summary", movie.getSummary());
            model.addAttribute("rating", movie.getRating());
            model.addAttribute("productionList", productions);
            model.addAttribute("currentMovieProduction", movie.getProduction().getName());
            model.addAttribute("movie", movie);
            return "editMovie";
        }
        return "redirect:/products?error=MovieNotFound";
    }

    @PostMapping("/saveMovie/{id}")
    public String submitEditedMovie(@PathVariable Long id,
                                    @RequestParam String movieNewTitle,
                                    @RequestParam String movieNewSummary,
                                    @RequestParam String movieNewRating,
                                    @RequestParam String selectedNewProduction) {
        Production newProduction = productionService.findAll().stream().filter(r -> r.getName().equals(selectedNewProduction)).findFirst().get();

        movieService.edit(id, movieNewTitle, movieNewSummary, movieNewRating, newProduction);

        return "redirect:/movies";
    }

    @GetMapping("/add")
    public String editMovie(Model  model) {
        List<Production> productions = productionService.findAll();
        model.addAttribute("productionList", productions);
        return"addMovie";
    }

    @PostMapping("/saveMovie")
    public String addNewMovie(      @RequestParam String movieNewTitle,
                                    @RequestParam String movieNewSummary,
                                    @RequestParam String movieNewRating,
                                    @RequestParam String selectedNewProduction) {
        Production newProduction = productionService.findAll().stream().filter(r -> r.getName().equals(selectedNewProduction)).findFirst().get();

        movieService.save(movieNewTitle, movieNewSummary, movieNewRating, newProduction);
        return "redirect:/movies";
    }

}

