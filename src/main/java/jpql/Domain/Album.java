package jpql.Domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item{

    private String album;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album='" + album +
                ", id=" + this.getId() +
                ", name='" + this.getName() +
                '}';
    }
}
