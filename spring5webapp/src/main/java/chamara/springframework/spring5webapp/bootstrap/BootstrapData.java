package chamara.springframework.spring5webapp.bootstrap;

import chamara.springframework.spring5webapp.repositeries.AuthorRepository;
import chamara.springframework.spring5webapp.repositeries.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        
    }
}
