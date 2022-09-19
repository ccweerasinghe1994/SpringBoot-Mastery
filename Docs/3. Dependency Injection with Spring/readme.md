# Dependency Injection

## SOLID Principles of OOP

![img](../Img/22.png)
![img](../Img/23.png)
![img](../Img/24.png)
![img](../Img/25.png)
![img](../Img/26.png)
![img](../Img/27.png)
![img](../Img/28.png)
![img](../Img/29.png)
![img](../Img/30.png)
![img](../Img/31.png)
![img](../Img/32.png)
![img](../Img/33.png)
![img](../Img/34.png)
![img](../Img/35.png)

## Create Spring DI Example Project

![img](../Img/36.png)

## The Spring Context

run the app with the doc inside

![img](../Img/37.png)
create the controller

```java
package chamara.springdi.springdi.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String helloWorld() {
        System.out.println("Hello World");
        return "Hi Folks";
    }
}

```

get the context

```java
package chamara.springdi.springdi;

import chamara.springdi.springdi.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiEApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDiEApplication.class, args);

        MyController myController = (MyController) ctx.getBean("myController");

        String greeting = myController.helloWorld();

        System.out.println(greeting);
    }

}

```

## Basics of Dependency Injection

![img](../Img/38.png)
![img](../Img/39.png)
![img](../Img/40.png)
![img](../Img/41.png)
![img](../Img/42.png)
![img](../Img/43.png)
![img](../Img/44.png)
![img](../Img/45.png)

## Dependency Injection without Spring

let's create a interface for GreetingService

```java
package chamara.springdi.springdi.services;

public interface GreetingService {
    String sayGreeting();
}

```

then create a implementation for that

```java
package chamara.springdi.springdi.services;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World";
    }
}
```

let's first create the least preferred method.
using property injected method.

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {
    public GreetingService greetingService;

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}

```

let's create a test for it.

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyInjectedControllerTest {
    PropertyInjectedController controller;

    @BeforeEach
    void setup(){
        controller = new PropertyInjectedController();

        controller.greetingService = new GreetingServiceImpl();
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}
```

output

```shell
Hello World
```

---

let's use the second method using setters Injected Controller

```java
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

```

and write the test for it

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetterInjectedControllerTest {
    SetterInjectedController controller;
    @BeforeEach
    void setUp() {
        controller = new SetterInjectedController();
        controller.setGreeting(new GreetingServiceImpl());
    }

    @Test
    void getGreetingService() {
        System.out.println(controller.getGreeting());
    }
}
```

output

```shell
Hello World
```

---

now let's try the most preferred way using constructors

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;

public class ConstructorInjectedController {
    private final GreetingService greetingService;

    public ConstructorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}

```

and write the test for it

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorInjectedControllerTest {

    ConstructorInjectedController controller;
    @BeforeEach
    void setUp() {
        controller = new ConstructorInjectedController(new GreetingServiceImpl());
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}
```

output

```shell
Hello World
```

## Dependency Injection using Spring Framework

let's add the three ways we can inject dependencies in spring

```java
package chamara.springdi.springdi;

import chamara.springdi.springdi.controllers.ConstructorInjectedController;
import chamara.springdi.springdi.controllers.MyController;
import chamara.springdi.springdi.controllers.PropertyInjectedController;
import chamara.springdi.springdi.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiEApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDiEApplication.class, args);

        MyController myController = (MyController) ctx.getBean("myController");

        String greeting = myController.helloWorld();

        System.out.println(greeting);

        System.out.println("--------- START PROPERTY ------------------");
        PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
        System.out.println(propertyInjectedController.getGreeting());
        System.out.println("--------- END PROPERTY ------------------");

        System.out.println("--------- START SETTER ------------------");
        SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
        System.out.println(setterInjectedController.getGreeting());
        System.out.println("--------- END SETTER ------------------");

        System.out.println("--------- START CONSTRUCTOR ------------------");
        ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
        System.out.println(constructorInjectedController.getGreeting());
        System.out.println("--------- END CONSTRUCTOR ------------------");
    }

}

```

Start With Property
we have to add the @Autowired to explicitly tell Spring to manage the DI

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {
    @Autowired
    public GreetingService greetingService;

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}

```

in the service we have to tell Spring to manage the Service

```java
package chamara.springdi.springdi.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World";
    }
}

```

now let's check the Setter method.
we have to use the @Autowired and @Controller stereotypes.

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {
    private GreetingService greetingService;

    public String getGreeting() {
        return this.greetingService.sayGreeting();
    }

    @Autowired
    public void setGreeting(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}

```

In the Constructor method we only have to add the @Controller stereotype. because Spring do the DI automatically

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {
    private final GreetingService greetingService;

    public ConstructorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}

```

output

```shell
--------- START PROPERTY ------------------
Hello World
--------- END PROPERTY ------------------
--------- START SETTER ------------------
Hello World
--------- END SETTER ------------------
--------- START CONSTRUCTOR ------------------
Hello World
--------- END CONSTRUCTOR ------------------
```

## Using Qualifiers

```java

```

```java

```

## Primary Beans

## Spring Profiles

## Default Profile

## Dependency Injection Assignment

## Spring Bean Life Cycle

## Spring Bean Life Cycle Demo

## Flashcards

## Open Closed Principle

## Interface Segregation Principle

## Dependency Inversion Principle

## Interface Naming Conventions

## Spring Pet Clinic - POJO Data Model

## Spring Pet Clinic - Multi-Module Maven Builds

## Spring Pet Clinic - Using the Maven Release Plugin

## Spring Pet Clinic - Create Interfaces for Services

## Spring Pet Clinic - Implement Base Entity
