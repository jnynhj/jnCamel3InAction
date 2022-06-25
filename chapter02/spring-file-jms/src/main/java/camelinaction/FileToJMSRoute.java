package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileToJMSRoute extends RouteBuilder {
    public void configure() {
        from("file:data/inbox?noop=true")
            .process(new Processor() {
                        public void process(Exchange exchange) throws Exception {
                        System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
                        }
                    })
             .to("{{output.queue}}");
    }
}
