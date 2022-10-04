package chamara.spring.jokes.controllers;

import chamara.spring.jokes.services.JokersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokesController {
    private final JokersService jokersService;

    public JokesController(JokersService jokersService) {
        this.jokersService = jokersService;
    }
    @RequestMapping("/jokes")
    public String getJokersService() {
        return jokersService.joke();
    }
}
