package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLineRunner is an interface we want to implement
// This will tell Spring to look for instances of this type and when it finds them it will run them

@Component
// Component tells the framework to detect below as a spring managed component
public class BootStrapData implements CommandLineRunner {

//    This is done to use dependency injection
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

//    This is setting up the constructor, and when the components are implemented
//    spring will use dependency injection into the constructor for both repositories
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher foobarpublisher = new Publisher();
        foobarpublisher.setName("FooBar Publishing");
        foobarpublisher.setAddressOne("123 Bar Ln");
        foobarpublisher.setCity("Memphis");
        foobarpublisher.setState("TN");
        foobarpublisher.setZip("38103");
        publisherRepository.save(foobarpublisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(foobarpublisher);
        foobarpublisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "418300");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(foobarpublisher);
        foobarpublisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(foobarpublisher);

        System.out.println("Number of FooBarPublisher books:" + " " + foobarpublisher.getBooks().size());
    }
}
