package jpql.Domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie  extends Item {

    private String movie;

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie='" + movie +
                ", id=" + this.getId() +
                ", name='" + this.getName() +
                '}';
    }
}
