package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Movie {
    private String title;
    private String summary;
    private double rating;
    private int numberOfOrders;
    private long id;
    private Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.id=(long) (Math.random()*1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production=production;
    }

    public int getNumberOfTickets(){
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders){
        this.numberOfOrders=numberOfOrders;
    }
}
