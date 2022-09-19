package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {
    private GreetingService greetingService;

    public String getGreeting() {
        return this.greetingService.sayGreeting();
    }

    @Autowired
    public void setGreeting(@Qualifier("setterInjectedGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
