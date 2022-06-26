package camelinaction;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import javax.jms.ConnectionFactory;

public class OrderRouterTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        // create CamelContext
        CamelContext camelContext = super.createCamelContext();
        
        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = 
            new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        return camelContext;
    }
    
    @Test
    public void testPlacingOrders() throws Exception {
//        getMockEndpoint("mock:xml").expectedMessageCount(1);
        getMockEndpoint("mock:accounting").expectedMessageCount(1);
        getMockEndpoint("mock:production").expectedMessageCount(1);
        getMockEndpoint("mock:csv").expectedMessageCount(2);
        getMockEndpoint("mock:bad").expectedMessageCount(1);
        getMockEndpoint("mock:continued").expectedMessageCount(4);

        assertMockEndpointsSatisfied();
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // load file orders from src/data into the JMS queue
                from("file:src/data_full?noop=true").to("jms:incomingOrders");
        
                // content-based router
                from("jms:incomingOrders")
                    .choice()
                        .when(header("CamelFileName").endsWith(".xml"))
                            .to("jms:xmlOrders")
                        .when(header("CamelFileName").regex("^.*(csv|csl)$"))
                            .to("jms:csvOrders")
                        .otherwise()
                            .to("jms:badOrders").stop()
                        // closed choice block using the end method
                        .end()
                        // each destination with the choice except the one with stop(), the message will be routed
                        // to the continuedProcessing queue as well.
                        .to("jms:continuedProcessing");
                
                // test that our route is working
                from("jms:xmlOrders")
                    // The XPath expression will evaluate true for orders that don’t have the test attribute.
                    .filter(xpath("/order[not(@test)]"))
                    .log("Received XML order: ${header.CamelFileName}")
                    // By default, the multicast sends message copies sequentially.
                    // Sending messages in parallel using the multicast involves only one extra DSL method:
                    // parallelProcessing.
                    .multicast().parallelProcessing()
                    .to("jms:accounting", "jms:production");
//                    .to("mock:xml");

                from("jms:accounting")
                        .log("Accounting received order: ${header.CamelFileName}")
                        .to("mock:accounting");

                from("jms:production")
                        .log("Production received order: ${header.CamelFileName}")
                        .to("mock:production");

                from("jms:csvOrders")
                    .log("Received CSV order: ${header.CamelFileName}")
                    .to("mock:csv");

                from("jms:badOrders")
                        .log("Received bad order: ${header.CamelFileName}")
                        .to("mock:bad");

                from("jms:continuedProcessing")
                        .log("Received continued order: ${header.CamelFileName}")
                        .to("mock:continued");

            }
        };
    }
}
