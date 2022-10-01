package chamara.springframework.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @SpringBootApplication
    public static class SpringPetClinicApplication {

        public static void main(String[] args) {
            SpringApplication.run(SpringPetClinicApplication.class, args);
        }

    }
}