package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * A Camel route in Spring Boot.
 *
 * Notice that we use @Component on the class to make the route automatic discovered by Spring Boot
 */
@Component
public class HelloRoute extends RouteBuilder {

    // see the application.properties file how we map the context-path
    // of the camel-servlet that the rest-dsl will use

    @Override
    public void configure() throws Exception {

        // use jetty for rest service
        restConfiguration().port(8080)
                .contextPath("/api/*")
                // turn on json binding
                .bindingMode(RestBindingMode.json)
                // turn off binding error on empty beans
                .dataFormatProperty("disableFeatures", "FAIL_ON_EMPTY_BEANS")
                // enable swagger api documentation
                .apiContextPath("api-doc")
                .apiProperty("api.title", "Spring Boot Camel Hello Rest API")
                .apiProperty("api.version", "1.0")
                .enableCORS(true);

        // define a Camel REST service using the rest-dsl
        // where we define a GET /hello as a service that routes to the hello route
        // we will cover rest-dsl in chapter 10


        rest("/").produces("text/plain")
//            .consumes(MediaType.APPLICATION_JSON_VALUE)
//            .produces(MediaType.APPLICATION_JSON_VALUE)
            .get("hello")
            .to("direct:hello");

        from("direct:hello")
            .transform().simple("Hello from Spring Boot and Camel.");
    }
}
