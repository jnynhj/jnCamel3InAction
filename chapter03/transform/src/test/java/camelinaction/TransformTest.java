package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

/**
 * How to use transform() DSL
 */
public class TransformTest extends CamelTestSupport {

    @Test
    public void testTransform() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedBodiesReceived("Hello<br/>How are you?");

        template.sendBody("direct:start", "Hello\nHow are you?");

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Enable tracing: Camel’s tracer is used for logging message details during routing, where you can
                // see the route path of each message as they happen. Details of the message is also logged such as the
                // message body.
                context.setTracing(true);

                from("direct:start")
                    .transform(body().regexReplaceAll("\n", "<br/>"))
                    .to("mock:result");
            }
        };
    }
}
