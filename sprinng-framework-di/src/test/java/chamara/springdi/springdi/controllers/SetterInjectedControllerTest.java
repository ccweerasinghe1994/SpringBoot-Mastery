package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.ConstructorGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetterInjectedControllerTest {
    SetterInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new SetterInjectedController();
        controller.setGreeting(new ConstructorGreetingService());
    }

    @Test
    void getGreetingService() {
        System.out.println(controller.getGreeting());
    }
}