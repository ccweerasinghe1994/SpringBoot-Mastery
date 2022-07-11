package chamara.springframework.spring5webapp.bootstrap;

import chamara.springframework.spring5webapp.domain.Author;
import chamara.springframework.spring5webapp.domain.Book;
import chamara.springframework.spring5webapp.repositeries.AuthorRepository;
import chamara.springframework.spring5webapp.repositeries.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("eric", "evans");
        Book add = new Book("Design driven development", "123123123");
        eric.getBooks().add(add);
        add.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(add);

        Author rod = new Author("rod", "Johnson");
        Book noEJB = new Book("J2EE", "546234121231");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books : " + bookRepository.count());
    }
}
