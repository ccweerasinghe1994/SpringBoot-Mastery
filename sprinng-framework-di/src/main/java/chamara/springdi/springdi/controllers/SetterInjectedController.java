package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;

public class SetterInjectedController {
    private GreetingService greetingService;

    public String getGreeting() {
        return this.greetingService.sayGreeting();
    }

    public void setGreeting(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
