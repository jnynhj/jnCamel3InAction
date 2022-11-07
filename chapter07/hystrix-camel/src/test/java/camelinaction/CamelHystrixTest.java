package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Camel with Hystrix using Java DSL
 */
public class CamelHystrixTest extends CamelTestSupport {
    private CamelContext context;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        SimpleRegistry registry = new SimpleRegistry();
        // register the counter service
        registry.bind("counter", new CounterService());
        // tell Camel to use our SimpleRegistry
        context = new DefaultCamelContext(registry);

        // create a producer template to use for testing
        template = context.createProducerTemplate();

        context.addRoutes(new HystrixRoute());
        context.start();
    }

//    @Override
//    protected RoutesBuilder createRouteBuilder() throws Exception {
//        // use the hystrix route
//        return new HystrixRoute();
//    }

    @Test
    public void testCamelHystrix() throws Exception {
        Object out1 = template.requestBody("direct:start", "Hello");
        assertEquals("Count 1", out1);

        Object out2 = template.requestBody("direct:start", "Hello");
        Assertions.assertEquals("Count 2", out2);

        Object out3 = template.requestBody("direct:start", "Hello");
        Assertions.assertEquals("Count 3", out3);

        Object out4 = template.requestBody("direct:start", "Hello");
        Assertions.assertEquals("Count 4", out4);

        // should fail the 5th time
        try {
            template.requestBody("direct:start", "Hello");
            Assertions.fail("Should have thrown exception");
        } catch (Exception e) {
            IOException cause = Assertions.assertInstanceOf(IOException.class, e.getCause().getCause());
            Assertions.assertEquals("Forced error", cause.getMessage());
        }
    }
}
