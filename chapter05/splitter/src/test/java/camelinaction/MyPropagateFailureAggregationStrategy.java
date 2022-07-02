package camelinaction;


import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class MyPropagateFailureAggregationStrategy implements AggregationStrategy {
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange.getException() != null) {
            if (oldExchange == null) {
                return newExchange;
            } else {
                oldExchange.setException(
                        newExchange.getException());
                return oldExchange;
            }
        }
        if (oldExchange == null) {
            return newExchange;
        }
        String body = newExchange.getIn().getBody(String.class);
        String existing = oldExchange.getIn().getBody(String.class);
        oldExchange.getIn().setBody(existing + "+" + body);
        return oldExchange;
    }
}