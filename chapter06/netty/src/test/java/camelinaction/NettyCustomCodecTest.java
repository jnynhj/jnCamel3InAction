package camelinaction;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.support.SimpleRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import javax.jms.ConnectionFactory;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentClientAcknowledge;

public class NettyCustomCodecTest extends CamelTestSupport {

    private static final String STATUS_GOOD = "MachineID=2371748;Status=Good";

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms", jmsComponentClientAcknowledge(connectionFactory));

        return camelContext;
    }

    protected SimpleRegistry createCamelRegistry() throws Exception {
        SimpleRegistry simple = new SimpleRegistry();
        simple.bind("welderDecoder", new WelderDecoder());
        simple.bind("welderEncoder", new WelderEncoder());
        return simple;
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            
            @Override
            public void configure() throws Exception {
                from("netty:tcp://localhost:8998?encoders=#welderEncoder&decoders=#welderDecoder&sync=false").to("jms:operations");
                
                from("jms:operations").to("mock:end");
            }
        };
    }

    @Test
    public void testSendToTcp() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:end");
        mock.expectedBodiesReceived(STATUS_GOOD);

        template.sendBody("netty:tcp://localhost:8998?encoders=#welderEncoder&decoders=#welderDecoder&sync=false", "23717481");

        mock.assertIsSatisfied();
    }

}
