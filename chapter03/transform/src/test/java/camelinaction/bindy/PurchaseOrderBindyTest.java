package camelinaction.bindy;

import junit.framework.TestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PurchaseOrderBindyTest extends TestCase {

    @Test
    public void testBindy() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(createRoute());
        context.start();

        MockEndpoint mock = context.getEndpoint("mock:result", MockEndpoint.class);
        mock.expectedBodiesReceived("Camel in Action,39.95,1\n");

        PurchaseOrder order = new PurchaseOrder();
        order.setAmount(1);
        order.setPrice(new BigDecimal("39.95"));
        order.setName("Camel in Action");

        ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:toCsv", order);

        mock.assertIsSatisfied();
    }

    public RouteBuilder createRoute() {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:toCsv")
                        .marshal().bindy(BindyType.Csv, camelinaction.bindy.PurchaseOrder.class)
                        .to("mock:result");
            }
        };
    }

}
