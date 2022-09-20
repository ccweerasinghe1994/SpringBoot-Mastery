# Dependency Injection

- [Dependency Injection](#dependency-injection)
  - [SOLID Principles of OOP](#solid-principles-of-oop)
  - [Create Spring DI Example Project](#create-spring-di-example-project)
  - [The Spring Context](#the-spring-context)
  - [Basics of Dependency Injection](#basics-of-dependency-injection)
  - [Dependency Injection without Spring](#dependency-injection-without-spring)
  - [Dependency Injection using Spring Framework](#dependency-injection-using-spring-framework)
  - [Using Qualifiers](#using-qualifiers)
  - [Primary Beans](#primary-beans)
  - [Spring Profiles](#spring-profiles)
  - [Spring Bean Life Cycle](#spring-bean-life-cycle)
  - [Questions](#questions)
  - [Spring Bean Life Cycle Demo](#spring-bean-life-cycle-demo)
  - [Flashcards](#flashcards)
  - [Open Closed Principle](#open-closed-principle)
  - [Interface Segregation Principle](#interface-segregation-principle)
  - [Dependency Inversion Principle](#dependency-inversion-principle)
  - [Interface Naming Conventions](#interface-naming-conventions)
  - [Spring Pet Clinic - POJO Data Model](#spring-pet-clinic---pojo-data-model)
  - [Spring Pet Clinic - Multi-Module Maven Builds](#spring-pet-clinic---multi-module-maven-builds)
  - [Spring Pet Clinic - Using the Maven Release Plugin](#spring-pet-clinic---using-the-maven-release-plugin)
  - [Spring Pet Clinic - Create Interfaces for Services](#spring-pet-clinic---create-interfaces-for-services)
  - [Spring Pet Clinic - Implement Base Entity](#spring-pet-clinic---implement-base-entity)

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

```bash
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

```bash
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

```bash
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

```bash
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

let's create separate Services for each Type Of dependency injections.

PropertyInjectedGreetingService

```java
package chamara.springdi.springdi.services;

import org.springframework.stereotype.Service;

@Service
public class PropertyInjectedGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - Property";
    }
}

```

SetterInjectedGreetingService

```java
package chamara.springdi.springdi.services;

import org.springframework.stereotype.Service;

@Service
public class SetterInjectedGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - Setter";
    }
}

```

ConstructorGreetingService

```java
package chamara.springdi.springdi.services;

import org.springframework.stereotype.Service;

@Service
public class ConstructorGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - Constructor";
    }
}

```

let's add @Qualifier to let spring know which service to Implement

ConstructorInjectedController

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {
    private final GreetingService greetingService;

    public ConstructorInjectedController(@Qualifier("constructorGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}

```

PropertyInjectedController

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {
    @Qualifier("propertyInjectedGreetingService")
    @Autowired
    public GreetingService greetingService;

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}

```

SetterInjectedController

```java
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

```

output

```bash
--------- START PROPERTY ------------------
Hello World - Property
--------- END PROPERTY ------------------
--------- START SETTER ------------------
Hello World - Setter
--------- END SETTER ------------------
--------- START CONSTRUCTOR ------------------
Hello World - Constructor
--------- END CONSTRUCTOR ------------------
```

## Primary Beans

let's create a PrimaryGreetingService with @Primary context annotation

```java
package chamara.springdi.springdi.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PrimaryGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World from Primary Bean ";
    }
}

```

let's change the MyController to use the Constructor DI

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    private final GreetingService greetingService;

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}

```

And Call it inside the Main Method

```java
 public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDiEApplication.class, args);

        System.out.println("--------- START PRIMARY BEAN ------------------");
        MyController myController = (MyController) ctx.getBean("myController");
        System.out.println(myController.sayHello());
        System.out.println("--------- END PRIMARY BEAN ------------------");

```

```bash
--------- START PRIMARY BEAN ------------------
Hello World from Primary Bean
--------- END PRIMARY BEAN ------------------
```

## Spring Profiles

let's create a new controllers

I18nSpanishGreetingService

```java
package chamara.springdi.springdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("SP")
@Service("i18nService")
public class I18nSpanishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hola mundo";
    }
}
```

I18nEnglishGreetingService

```java
package chamara.springdi.springdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("EN")
@Service("i18nService")
public class I18nEnglishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - English";
    }
}

```

I18nController

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class I18nController {
    private final GreetingService greetingService;

    public I18nController(@Qualifier("i18nService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String syaHello() {
        return greetingService.sayGreeting();
    }
}

```

application.properties

```ENV
spring.profiles.active=EN
```

SpringDiEApplication

```java
package chamara.springdi.springdi;

import chamara.springdi.springdi.controllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiEApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDiEApplication.class, args);

        I18nController i18nController = (I18nController) ctx.getBean("i18nController");
        System.out.println(i18nController.syaHello());


```

Output```java

````

```bash
Hello World - English
````

## Default Profile

in side the services we can provide the default value to set that service as the default profile.

I18nEnglishGreetingService

```java
@Profile({"EN", "default"})
@Service("i18nService")
public class I18nEnglishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - English";
    }
}

```

comment out the active profile

application.properties

```env
#spring.profiles.active=EN
```

output

```bash
Hello World - English
```

## Spring Bean Life Cycle

![img](../Img/46.png)
![img](../Img/47.png)
![img](../Img/48.png)
![img](../Img/49.png)
![img](../Img/50.png)
![img](../Img/51.png)
![img](../Img/52.png)

## Questions

Interface
PetService

```java
package chamara.springdi.springdi.services;

public interface PetService {
    String GetPetType();
}

```

Service
CatPetService

```java
package chamara.springdi.springdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("cat")
public class CatPetService implements PetService {
    @Override
    public String GetPetType() {
        return "Cats Are the Best!";
    }
}
```

PetController

```java
package chamara.springdi.springdi.controllers;

import chamara.springdi.springdi.services.PetService;
import org.springframework.stereotype.Controller;

@Controller
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    public String whichPetIsTheBest() {
        return petService.GetPetType();
    }
}

```

add the cat profile

```ENV
spring.profiles.active=cat,EN
```

Run The Code

```java
package chamara.springdi.springdi;

import chamara.springdi.springdi.controllers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiEApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDiEApplication.class, args);


        System.out.println("--------- START PET SERVICE ------------------");
        PetController petController = (PetController) ctx.getBean("petController", PetController.class);
        System.out.println(petController.whichPetIsTheBest());
        System.out.println("--------- END PET SERVICE ------------------");

```

output

```bash
--------- START PET SERVICE ------------------
Cats Are the Best!
--------- END PET SERVICE ------------------
```

## Spring Bean Life Cycle Demo

```java

```

```java

```

```java

```

```java

```

```java

```

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
