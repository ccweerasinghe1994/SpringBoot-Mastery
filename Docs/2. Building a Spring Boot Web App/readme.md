# 2. Building a Spring Boot Web App
- [2. Building a Spring Boot Web App](#2-building-a-spring-boot-web-app)
  - [6. JPA Entities](#6-jpa-entities)
    - [JPA Model](#jpa-model)
    - [Creating POJOS](#creating-pojos)
    - [What is POJO](#what-is-pojo)
    - [Let's create POJOS for Author](#lets-create-pojos-for-author)
      - [Code Examples](#code-examples)
  - [7. Equality in Hibernate](#7-equality-in-hibernate)
    - [Let's Generate Equals and hashCode methods](#lets-generate-equals-and-hashcode-methods)
      - [Code Examples](#code-examples-1)
    - [Let's Generate toString method](#lets-generate-tostring-method)
  - [8. Spring Data Repositories](#8-spring-data-repositories)
    - [Repositories](#repositories)
  - [9. Initializing Data with Spring](#9-initializing-data-with-spring)
  - [11. Publisher Relationships](#11-publisher-relationships)
  - [12. H2 Database Console](#12-h2-database-console)
  - [13. Introduction to Spring MVC](#13-introduction-to-spring-mvc)
  - [14. Configuring Spring MVC Controllers](#14-configuring-spring-mvc-controllers)
  - [15. Thymeleaf Templates](#15-thymeleaf-templates)
  - [17. Introduction to Spring Pet Clinic](#17-introduction-to-spring-pet-clinic)
  - [18. Running Spring Pet Clinic](#18-running-spring-pet-clinic)
  - [19. Intro to SFG Version of Spring PetClinic Application](#19-intro-to-sfg-version-of-spring-petclinic-application)
  - [20. Spring Pet Clinic - Initializing Spring PetClinic Application](#20-spring-pet-clinic---initializing-spring-petclinic-application)
  - [21. Spring Pet Clinic - Task Planning](#21-spring-pet-clinic---task-planning)

## 6. JPA Entities
### JPA Model
![](../Img/1.png)

Author and Books have Many to Many Relationship.
### Creating POJOS
### What is POJO
`
POJO stands for Plain Old Java Object. It is an ordinary Java object, not bound by any special restriction other than those forced by the Java Language Specification and not requiring any classpath. POJOs are used for increasing the readability and re-usability of a program. POJOs have gained the most acceptance because they are easy to write and understand. They were introduced in EJB 3.0 by Sun microsystems.
`
- [Reference Link](https://www.geeksforgeeks.org/pojo-vs-java-beans/#:~:text=POJO%20classes,re%2Dusability%20of%20a%20program.)
### Let's create POJOS for Author 
#### Code Examples

Author Class

```java
package chamara.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Book> books;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author(String firstName, String lastName, Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}

```
Book Class

```java
package chamara.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String isbn;
    @ManyToMany(mappedBy = "authors")
    private Set<Author> authors;

    public Book() {
    }

    public Book(String title, String isbn, Set<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}


```

![](../Img/2.png)
## 7. Equality in Hibernate
### Let's Generate Equals and hashCode methods
For Both Author and Book POJOs
#### Code Examples

```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
```
### Let's Generate toString method
```java
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

```
## 8. Spring Data Repositories
### Repositories
let's create a package for the repositories
![](../Img/3.png)

BookRepository
```java
package chamara.springframework.spring5webapp.repositeries;

import chamara.springframework.spring5webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}

```
Author Repository

```java
package chamara.springframework.spring5webapp.repositeries;

import chamara.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

```

let's see what the CRUD repository gives us.

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.data.repository;

import java.util.Optional;

@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();
}

```
so we have access to these methods by default.
## 9. Initializing Data with Spring
## 11. Publisher Relationships
## 12. H2 Database Console
## 13. Introduction to Spring MVC
## 14. Configuring Spring MVC Controllers
## 15. Thymeleaf Templates
## 17. Introduction to Spring Pet Clinic
## 18. Running Spring Pet Clinic
## 19. Intro to SFG Version of Spring PetClinic Application
## 20. Spring Pet Clinic - Initializing Spring PetClinic Application
## 21. Spring Pet Clinic - Task Planning
