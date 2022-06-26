package camelinaction;

import java.io.File;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import javax.jms.ConnectionFactory;

public class OrderRouterWithMulticastSOETest extends CamelTestSupport {

//    @Override
//    public void setUp() throws Exception {
    //    deleteDirectory("activemq-data");
//        File file = FileUtils.getFile("activemq-data");
//        FileUtils.deleteDirectory(file);
//        super.setUp();
//    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        // create CamelContext
        CamelContext camelContext = super.createCamelContext();
        
        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = 
        //    new ActiveMQConnectionFactory("vm://localhost");
            new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        return camelContext;
    }
    
    @Test
    public void testPlacingOrders() throws Exception {
        getMockEndpoint("mock:accounting_before_exception").expectedMessageCount(1);
        getMockEndpoint("mock:accounting").expectedMessageCount(0);
        getMockEndpoint("mock:production").expectedMessageCount(0);
        assertMockEndpointsSatisfied();
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // load file orders from src/data into the JMS queue
                from("file:src/data?noop=true").to("jms:incomingOrders");
        
                // content-based router
                from("jms:incomingOrders")
                    .choice()
                        .when(header("CamelFileName").endsWith(".xml"))
                            .to("jms:xmlOrders")  
                        .when(header("CamelFileName").regex("^.*(csv|csl)$"))
                            .to("jms:csvOrders")
                        .otherwise()
                            .to("jms:badOrders");        
                
                from("jms:xmlOrders")
                    .multicast()
                    // The stopOnException feature of the multicast. When enabled, this feature
                    // will stop the multicast on the first exception caught, so you can take any
                    // necessary action. In our example, the exception could have happened after
                    // the message had been consumed by both the accounting and production queues,
                    //essentially nullifying the stopOnException effect. In our test case we decided
                    // to use synchronous direct //endpoints which would allow us to test this
                    // feature of the multicast.
                    .stopOnException()
                    .to("jms:accounting", "jms:production");
               
                from("jms:accounting")      
                    .to("mock:accounting_before_exception")
                    .log("Accounting received order: ${header.CamelFileName}")
                    .to("mock:accounting");                
                
                from("jms:production")
                        .throwException(Exception.class, "I failed!")
                    .log("Production received order: ${header.CamelFileName}")
                    .to("mock:production");                
            }
        };
    }
}
