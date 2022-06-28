package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import javax.xml.bind.JAXBContext;

public class PurchaseOrderJaxbTest extends CamelTestSupport {

    @Test
    public void testJaxb() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:order");
        mock.expectedMessageCount(1);
        mock.message(0).body().isInstanceOf(PurchaseOrder.class);

        PurchaseOrder order = new PurchaseOrder();
        order.setName("Camel in Action");
        order.setPrice(6999);
        order.setAmount(1);

        template.sendBody("direct:order", order);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // XML Data Format
                JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
                JAXBContext con = JAXBContext.newInstance(PurchaseOrder.class);
                xmlDataFormat.setContext(con);
                from("direct:order")
                        .marshal(xmlDataFormat)
                        .to("seda:queue:order");

                from("seda:queue:order")
                        .unmarshal(xmlDataFormat)
                        .to("mock:order");
            }
        };
    }
}
