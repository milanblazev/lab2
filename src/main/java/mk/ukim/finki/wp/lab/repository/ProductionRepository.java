package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductionRepository {
    List<Production> ProductionList=null;

    @PostConstruct
    public void init() {
        ProductionList = new ArrayList<Production>();
        ProductionList.add(new Production("Marvel Studios", "United States", "500 South Buena Vista Street, Burbank, CA 91521, USA"));

        ProductionList.add(new Production("DreamWorks Pictures", "United States", "100 Universal City Plaza, Universal City, CA 91608, USA"));

        ProductionList.add(new Production("Lionsgate Films", "United States", "2700 Colorado Avenue, Santa Monica, CA 90404, USA"));

        ProductionList.add(new Production("Focus Features", "United States", "100 Universal City Plaza, Universal City, CA 91608, USA"));

        ProductionList.add(new Production("A24", "United States", "601 West 26th Street, Suite 1740, New York, NY 10001, USA"));
    }
    public List<Production> findAll(){
        return ProductionList;
}}