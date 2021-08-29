package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Movie  extends Item{

    private String movie;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
