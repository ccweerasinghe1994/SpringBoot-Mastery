package chamara.springdi.springdi;

import chamara.springdi.springdi.controllers.*;
import chamara.springdi.springdi.services.LifeCycleDemoBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDiEApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDiEApplication.class, args);

        LifeCycleDemoBean lifeCycleDemoBean = (LifeCycleDemoBean) ctx.getBean("lifeCycleDemoBean");
        System.out.println("--------- START PET SERVICE ------------------");
        PetController petController = (PetController) ctx.getBean("petController", PetController.class);
        System.out.println(petController.whichPetIsTheBest());
        System.out.println("--------- END PET SERVICE ------------------");

        System.out.println("--------- START DEFAULT PROFILE ------------------");
        I18nController i18nController = (I18nController) ctx.getBean("i18nController");
        System.out.println(i18nController.syaHello());
        System.out.println("--------- END DEFAULT PROFILE ------------------");

        System.out.println("--------- START PRIMARY BEAN ------------------");
        MyController myController = (MyController) ctx.getBean("myController");
        System.out.println(myController.sayHello());
        System.out.println("--------- END PRIMARY BEAN ------------------");


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
