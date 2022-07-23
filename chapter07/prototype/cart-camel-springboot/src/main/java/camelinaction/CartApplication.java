package camelinaction;

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.main.Main;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Shopping cart micro service
 */

@SpringBootApplication
public class CartApplication {
    public static void main(String[] args) {
            SpringApplication.run(CartApplication.class, args);
    }

    /*
    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean
                (new CamelHttpTransportServlet(), contextPath+"/*");
        servlet.setName("CartServlet");
        return servlet;
    }
    */
}
