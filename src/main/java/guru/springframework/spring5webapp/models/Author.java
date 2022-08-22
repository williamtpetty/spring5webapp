package guru.springframework.spring5webapp.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity // @ sign indicates annotation, annotating the Author class as an entity
public class Author {

    @Id // annotate as the ID
    @GeneratedValue(strategy = GenerationType.AUTO) // indicates PK automatically generated/maintained by DB
    private Long id; // private indicates immutable attribute
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors") // annotation to define table relationships
    private Set<Book> books = new HashSet<>(); // initializes the relationship between Authors and Books


    public Author() { // JPA requires a zero args constructor
    }

    public Author(Long id) {
        this.id = id;
    } // ID Constructor

    public Author(String firstName, String lastName) { // Class constructor
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    Getter and Setter methods
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { // void indicates no return value
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override // toString() returns properties defined
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
