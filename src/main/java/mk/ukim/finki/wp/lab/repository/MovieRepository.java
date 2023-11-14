package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {
    List<Movie> MovieList=null;
    @PostConstruct
    public void init(){
        MovieList = new ArrayList<>();
        MovieList.add(new Movie("Inception", "A thief who enters the dreams of others to steal their secrets gets a complex task to implant an idea into someone's mind. As he delves deeper into the subconscious, reality and dreams blur.", 8.8, new Production("A24", "United States", "601 West 26th Street, Suite 1740, New York, NY 10001, USA")));
        MovieList.add(new Movie("The Shawshank Redemption", "The story of a banker who is wrongly convicted of murder and his journey through the Shawshank State Penitentiary, where he forms deep friendships and seeks redemption.", 9.3, new Production("Focus Features", "United States", "100 Universal City Plaza, Universal City, CA 91608, USA")));
        MovieList.add(new Movie("The Godfather", "The powerful and influential Corleone family is at the center of this crime saga, following their patriarch's transformation from a reluctant mob boss to a ruthless leader.", 9.2, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("Pulp Fiction", "This film weaves interconnected stories of crime, redemption, and chaos in the Los Angeles underworld, featuring memorable characters and non-linear storytelling.", 8.9, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("The Dark Knight", "Batman faces off against the Joker, a sadistic criminal mastermind, in a battle of wits and wills that pushes Gotham City to its limits.", 9.0, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("Forrest Gump", "The life story of Forrest Gump, a man with low intelligence but a big heart, who inadvertently influences key moments in American history.", 8.8, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("The Matrix", "A computer hacker discovers a dystopian world controlled by machines and joins a group of rebels to fight against their oppressive rule.", 8.7, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("Gladiator", "A betrayed Roman general seeks revenge against the corrupt emperor who murdered his family and sent him into slavery as a gladiator.", 8.5, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "A young hobbit embarks on a perilous journey to destroy a powerful ring, joined by a diverse group of companions in a quest to save Middle-earth.", 8.8, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
        MovieList.add(new Movie("Schindler's List", "The true story of Oskar Schindler, a German businessman who saved the lives of over a thousand Polish-Jewish refugees during the Holocaust.", 8.9, new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA")));
    }
    public List<Movie> findAll(){
        return MovieList;
    }
    public List<Movie> searchMovies(String text){
       return MovieList.stream().filter(movie -> movie.getTitle().contains(text) || movie.getSummary().contains(text)).toList();
    }

    public void increaseTicketOrders(String title, int amount)
    {
        int currentNumberOfOrders = MovieList.stream().filter(r -> r.getTitle().equals(title)).findFirst().get().getNumberOfOrders();
        MovieList.stream().filter(r -> r.getTitle().equals(title)).findFirst().get().setNumberOfOrders(currentNumberOfOrders+amount);

    }

    public void deleteById(long id) {
        MovieList.removeIf(r->r.getId()==id);
    }

    public Optional<Movie> findById(Long id) {
        return MovieList.stream().filter(r -> r.getId() == id).findFirst();
    }

    public void save(String movieNewTitle, String movieNewSummary, String movieNewRating, Production newProduction) {
        MovieList.add(new Movie(movieNewTitle, movieNewSummary, Double.parseDouble(movieNewRating), newProduction));
    }

    public void edit(Long id, String movieNewTitle, String movieNewSummary, String movieNewRating, Production newProduction) {
        Movie foundMovie = MovieList.stream().filter(r -> r.getId() == id).findFirst().get();
        if(foundMovie != null)
        {
            foundMovie.setTitle(movieNewTitle);
            foundMovie.setSummary(movieNewSummary);
            foundMovie.setRating(Double.parseDouble(movieNewRating));
            foundMovie.setProduction(newProduction);
        }
    }
}
