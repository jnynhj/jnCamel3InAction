package camelinaction;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import javax.jms.ConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;

public class FtpToJMSWithDynamicToTest extends CamelTestSupport {

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
        getMockEndpoint("mock:incomingOrders").expectedMessageCount(1);
        assertMockEndpointsSatisfied();
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
//                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
//                context.addComponent("jms",
//                        JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

                // load file orders from src/data into the JMS queue
                from("file:src/data?noop=true")
                    .setHeader("myDest", constant("incomingOrders"))
                    .toD("jms:${header.myDest}");
                       
                // test that our route is working
                from("jms:incomingOrders")
                    .to("mock:incomingOrders");
            }
        };
    }
}
