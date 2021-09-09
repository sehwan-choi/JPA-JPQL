package jpql.Domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item{

    private String book;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book='" + book +
                ", id=" + this.getId() +
                ", name='" + this.getName() +
                '}';
    }
}
