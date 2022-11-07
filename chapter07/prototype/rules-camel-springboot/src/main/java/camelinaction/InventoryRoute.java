package camelinaction;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * Camel route that calls the legacy system
 */
@Component
public class InventoryRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // 7.4.1 Retry Pattern (P277)
        errorHandler(defaultErrorHandler()
            .maximumRedeliveries(5)
            .redeliveryDelay(2000));

        from("direct:inventory")
            // call the legacy system using JMS
            .to("jms:queue:inventory")
            // the returned data is in XML format so convert that to POJO using JAXB
            .unmarshal().jaxb("camelinaction");
    }

    /*
     * Setup JMS component
     */
    /*
    @Bean("jms")
    public static JmsComponent jmsComponent() {
        ActiveMQComponent jms = new ActiveMQComponent();
        jms.setBrokerURL("tcp://localhost:61616");
        return jms;
    }
    */
}
