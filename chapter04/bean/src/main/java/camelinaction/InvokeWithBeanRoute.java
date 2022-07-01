package camelinaction;

import org.apache.camel.builder.RouteBuilder;

/**
 * Using a bean in the route to invoke HelloBean.
 */
public class InvokeWithBeanRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:hello")
            // instantiate HelloBean once, and reuse and invoke the hello bean
            // In Java DSL, do not have to pre-register the bean in the registry. Instead, you can provide
            // the class name of the bean, and Camel will instantiate the bean on startup.
            .bean(HelloBean.class, "hello");
    }
}
