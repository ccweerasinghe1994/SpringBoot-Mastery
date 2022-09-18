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

```java

```

```java

```

```java

```

## Basics of Dependency Injection

## Dependency Injection without Spring

## Dependency Injection using Spring Framework

## Using Qualifiers

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
