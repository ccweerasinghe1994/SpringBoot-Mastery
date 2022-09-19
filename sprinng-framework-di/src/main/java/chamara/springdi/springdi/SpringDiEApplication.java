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
