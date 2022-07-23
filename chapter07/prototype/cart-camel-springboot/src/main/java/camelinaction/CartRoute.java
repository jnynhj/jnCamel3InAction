package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


/**
 * Shopping Cart REST service using Camel rest-dsl (to be covered in chapter 10)
 */
@Component
public class CartRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // use jetty for rest service
        restConfiguration()
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
                .get("hello")
                .to("direct:hello");

        from("direct:hello")
                .transform().simple("Hello from Spring Boot and Camel.");


        // define the rest service
        rest("/cart")
            .consumes(MediaType.APPLICATION_JSON_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
//            .consumes("application/json").produces("application/json")
            // get returns List<CartDto>
            .get("/")
            .outType(CartDto[].class)
            .description("Returns the items currently in the shopping cart")
            .to("bean:cart?method=getItems");

            // get accepts CartDto
        rest("/cart")
            .consumes(MediaType.APPLICATION_JSON_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .post()
            .type(CartDto.class)
            .description("Adds the item to the shopping cart")
            .to("bean:cart?method=addItem");
                /*
            .delete().description("Removes the item from the shopping cart")
                .param().name("itemId").description("Id of item to remove").endParam()
//                .to("bean:cartService?method=removeItem");
                .to("bean:cart?method=removeItem");
                */
    }
}
