package chamara.spring.jokes.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

@Service
public class JokesServiceImp implements JokersService {

    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public JokesServiceImp() {
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String joke() {
        return chuckNorrisQuotes.getRandomQuote();
    }
}
