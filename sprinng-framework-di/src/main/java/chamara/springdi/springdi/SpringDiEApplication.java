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
