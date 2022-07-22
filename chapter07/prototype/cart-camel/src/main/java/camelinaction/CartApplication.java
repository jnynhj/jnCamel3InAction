package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.main.Main;
import org.apache.camel.spi.Registry;
import org.apache.camel.support.DefaultRegistry;
import org.apache.camel.support.SimpleRegistry;

/**
 * Shopping cart micro service
 */
public class CartApplication {

    /**
     * Main class to run this example, such as from your Java editor
     */
    public static void main(String[] args) throws Exception {
        PropertiesComponent component = new PropertiesComponent();
        // load properties file form the classpath
        component.setLocation("classpath:cart.properties");

        Main main = new Main();
        main.bind("properties", component);
        main.bind("cart", new CartService());

        // add the route
        main.configure().addRoutesBuilder(new CartRoute());
        main.run();
    }
}
