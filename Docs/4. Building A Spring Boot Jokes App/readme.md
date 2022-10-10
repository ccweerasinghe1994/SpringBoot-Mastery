# [Home Page](../../README.md)

## 1. Introduction to Building a Spring Boot Jokes App

create a spring boot application which will return unique chuck norris jokes.

## 2. Assignment - Build a Spring Boot Jokes App

try to create the application by your self.

## 3. Creating the Spring Boot Project

create a spring application using spring.io using web and thymeleaf.

## 4. Adding Maven Dependencies

add this dependency to the application.

```xml
<dependency>
  <groupId>guru.springframework</groupId>
  <artifactId>chuck-norris-for-actuator</artifactId>
  <version>3.0.0-M4</version>
</dependency>
```

and update the dependency

## 5. Creating the Spring Service Layer

let's create the interface

```java
package chamara.spring.jokes.services;

public interface JokersService {
    String joke();
}
```

and the implementation

```java
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

```

## 6. Creating the Spring MVC Controller

let's create the controller.

```java
package chamara.spring.jokes.controllers;

import chamara.spring.jokes.services.JokersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokesController {
    private final JokersService jokersService;

    public JokesController(JokersService jokersService) {
        this.jokersService = jokersService;
    }
    @RequestMapping({"/",})
    public String showJoke(Model model){
        model.addAttribute("joke",jokersService.joke());

        return "jokes/jokes";
    };


}
```

## 7. Creating the View Layer

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>chuck noris jokes</title>
</head>
<body>
<h1>chuck norris Jokes</h1>
<p th:text="${joke}">Show jokes here</p>
</body>
</html>
```

output

```bash
chuck norris Jokes
Chuck Norris does infinite loops in 4 seconds.
```

## 8. Tips and Tricks - Custom Banner

## 9. Spring Pet Clinic - Refactor Services to Common Interface

## 10. Spring Pet Clinic - Create Index Page and Controller

## 11. Spring Pet Clinic - Using and Image for Custom Banner

## 12. Spring Pet Clinic - Create Index Page and Controller

## 13. Spring Pet Clinic - Task Planning

## 14. Spring Pet Clinic - Create Vet Page and Controller

## 15. Spring Pet Clinic - Create Owner Page and Controller
