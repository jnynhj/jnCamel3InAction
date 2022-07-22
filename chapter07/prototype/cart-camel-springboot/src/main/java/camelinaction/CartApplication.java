package camelinaction;

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.main.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Shopping cart micro service
 */

@SpringBootApplication
public class CartApplication {

    public static void main(String[] args) {
            SpringApplication.run(CartApplication.class, args);
    }
}
