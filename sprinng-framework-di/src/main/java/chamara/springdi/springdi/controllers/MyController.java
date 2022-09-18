package chamara.springdi.springdi.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String helloWorld() {
        System.out.println("Hello World");
        return "Hi Folks";
    }
}
