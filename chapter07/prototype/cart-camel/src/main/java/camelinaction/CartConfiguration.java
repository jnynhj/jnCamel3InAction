package camelinaction;

import org.apache.camel.Configuration;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * Configures the application.
 */
public class CartConfiguration {

    /**
     * Create the Camel properties component using CDI @Produces with the name: properties
     */
    PropertiesComponent propertiesComponent() {
        PropertiesComponent component = new PropertiesComponent();
        // load properties file form the classpath
        component.setLocation("classpath:cart.properties");
        return component;
    }

}
