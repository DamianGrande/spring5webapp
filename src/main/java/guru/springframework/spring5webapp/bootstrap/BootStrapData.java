package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("asd", "street", "city", "state", "zip");
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        this.publisherRepository.save(publisher);
        this.bookRepository.save(ddd);
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        noEJB.getAuthors().add(rod);
        rod.getBooks().add(noEJB);
        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        this.publisherRepository.save(publisher);
        this.bookRepository.save(noEJB);
        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + this.bookRepository.count());
        System.out.println("Number of Publishers: " + this.publisherRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
