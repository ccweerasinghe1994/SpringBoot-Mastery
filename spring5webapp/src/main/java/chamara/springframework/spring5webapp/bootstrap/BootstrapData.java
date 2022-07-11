package chamara.springframework.spring5webapp.bootstrap;

import chamara.springframework.spring5webapp.domain.Author;
import chamara.springframework.spring5webapp.domain.Book;
import chamara.springframework.spring5webapp.domain.Publisher;
import chamara.springframework.spring5webapp.repositeries.AuthorRepository;
import chamara.springframework.spring5webapp.repositeries.BookRepository;
import chamara.springframework.spring5webapp.repositeries.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St PetersBerg");
        publisher.setState("Fl");

        publisherRepository.save(publisher);
        
        System.out.println("Publisher Count : " + publisherRepository.count());

        Author eric = new Author("eric", "evans");
        Book add = new Book("Design driven development", "123123123");
        eric.getBooks().add(add);
        add.getAuthors().add(eric);

        add.setPublisher(publisher);
        publisher.getBooks().add(add);

        authorRepository.save(eric);
        bookRepository.save(add);
        publisherRepository.save(publisher);

        Author rod = new Author("rod", "Johnson");
        Book noEJB = new Book("J2EE", "546234121231");

        add.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books : " + bookRepository.count());
        System.out.println("Publisher Number of Books : " + publisher.getBooks().size());
    }
}
