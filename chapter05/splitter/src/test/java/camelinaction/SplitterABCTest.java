package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SplitterABCTest extends CamelTestSupport {

    @Test
    public void testSplitABC() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:split");
        mock.expectedBodiesReceived("A", "B", "C");

        List<String> body = new ArrayList<String>();
        body.add("A");
        body.add("B");
        body.add("C");

        template.sendBody("direct:start", body);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                    .split(body())
                        .log("Split line ${body}")
                        .to("mock:split");
            }
        };
    }
}
