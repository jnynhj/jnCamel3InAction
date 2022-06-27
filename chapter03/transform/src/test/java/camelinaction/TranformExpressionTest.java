package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

/**
 * How to use transform() DSL with inline Camel Expression that allows you to use Java code in its evaluate() method.
 */
public class TranformExpressionTest extends CamelTestSupport {

    @Test
    public void testTransformExpression() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedBodiesReceived("<body>Hello<br/>How are you?</body>");

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
                        .transform(new Expression() {
                            public <T> T evaluate(Exchange exchange, Class<T> type) {
                                String body = exchange.getIn().getBody(String.class);
                                body = body.replaceAll("\n", "<br/>");
                                body = "<body>" + body + "</body>";
                                return (T) body;
                            }
                        })
                        .to("mock:result");
            }
        };
    }
}
