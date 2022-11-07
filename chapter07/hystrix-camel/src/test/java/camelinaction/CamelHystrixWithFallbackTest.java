package camelinaction;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Camel with Hystrix and fallback using Java DSL
 */
public class CamelHystrixWithFallbackTest extends CamelTestSupport {

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

        context.addRoutes(new HystrixWithFallbackRoute());
        context.start();
    }

    @Test
    public void testCamelHystrixWithFallback() throws Exception {
        Object out1 = template.requestBody("direct:start", "Hello");
        assertEquals("Count 1", out1);

        Object out2 = template.requestBody("direct:start", "Hello");
        assertEquals("Count 2", out2);

        Object out3 = template.requestBody("direct:start", "Hello");
        assertEquals("Count 3", out3);

        Object out4 = template.requestBody("direct:start", "Hello");
        assertEquals("Count 4", out4);

        // should not fail the 5th time
        Object out5 = template.requestBody("direct:start", "Hello");
        assertEquals("No Counter", out5);
    }
}
