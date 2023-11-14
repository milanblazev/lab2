package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.services.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "")
public class MovieListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;

    public MovieListServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("form1Submit") != null)
        {
            String searchString = req.getParameter("searchText");
            float searchRating = Float.parseFloat(req.getParameter("searchRating"));

            IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);

            List<Movie> allMatchingMovies = this.movieService.listAll().stream().filter(
                    r->
                            r.getRating() >= searchRating
                                    ||
                                    r.getTitle().toLowerCase().contains(searchString.toLowerCase())
            ).toList();

            WebContext context = new WebContext(webExchange);
            context.setVariable("movies", allMatchingMovies);

            this.springTemplateEngine.process("listMovies.html",context,resp.getWriter());
        }
        else if(req.getParameter("form2Submit") != null)
        {
            String movieName = req.getParameter("movie-radio-button");
            Movie movie = movieService.searchMovies(movieName).stream().findFirst().get();
            long numberOfTickets = Long.parseLong(req.getParameter("numTickets"));

            TicketOrder ticketOrder = new TicketOrder(movieName, req.getHeader("User-Agent"), req.getRemoteAddr(), numberOfTickets);

            movieService.increaseTicketOrders(movieName,(int)numberOfTickets);

            req.getSession().setAttribute("ticketOrder", ticketOrder);
            resp.sendRedirect("/ticketOrder");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Movie mostOrderedMovie = movieService.listAll().get(0);
        for (Movie movie:
        movieService.listAll()){
            if(movie.getNumberOfOrders() > mostOrderedMovie.getNumberOfOrders())
                mostOrderedMovie=movie;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);
        context.setVariable("movies", movieService.listAll());
        context.setVariable("mostOrderedMovie", mostOrderedMovie);
        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );

    }
}
