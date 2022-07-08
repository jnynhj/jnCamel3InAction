package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.language.xpath.XPath;

public class ValidatorBean {

    public void validate(@XPath("/order/@name") String partName, Exchange exchange) {
        // only motors are valid parts in this simple test bean
        if ("motor".equals(partName)) {
            // getOut() deprecated, use getMessage()
            exchange.getMessage().setBody("Valid");
        } else {
            exchange.getMessage().setBody("Invalid");
        }
    }

}
