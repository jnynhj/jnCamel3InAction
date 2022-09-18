package camelinaction;

import org.apache.camel.builder.RouteBuilder;

public class InventoryRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jms:queue:inventory").routeId("inventory")
        .to("bean:inventory");
    }
}
