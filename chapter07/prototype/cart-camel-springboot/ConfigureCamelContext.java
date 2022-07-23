package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.RestConfiguration;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.stereotype.Component;

@Component
public class ConfigureCamelContext implements CamelContextConfiguration {

    @Override
    public void beforeApplicationStart(CamelContext camelContext) {
        RestConfiguration restConfiguration = new RestConfiguration();
        restConfiguration.setApiComponent("jetty");
        restConfiguration.setPort(8282);
        restConfiguration.setBindingMode(RestConfiguration.RestBindingMode.json);
//        restConfiguration.setDataFormatProperties("disableFeatures", "FAIL_ON_EMPTY_BEANS"));
        restConfiguration.setApiContextPath("api-doc");
        restConfiguration.setEnableCORS(true);
    }

    @Override
    public void afterApplicationStart(CamelContext camelContext) {
    }
}
