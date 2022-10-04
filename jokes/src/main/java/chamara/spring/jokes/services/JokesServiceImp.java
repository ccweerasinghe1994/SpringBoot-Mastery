package chamara.spring.jokes.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokesServiceImp implements JokersService{


    @Override
    public String joke() {
        return new ChuckNorrisQuotes().getRandomQuote();
    }
}
