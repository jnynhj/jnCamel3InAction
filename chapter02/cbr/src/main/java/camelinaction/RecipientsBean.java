package camelinaction;

import org.apache.camel.language.xpath.XPath;

/**
 * Recipient List bean that sends orders to the production and 
 * accounting queues if the order originated from a gold customer. 
 * Otherwise, the order is only sent to the accounting queue.
 * 
 * The recipient list annotation is used to accomplish this.
 */
public class RecipientsBean {

    public String[] recipients(@XPath("/order/@customer") String customer) {
        if (isGoldCustomer(customer)) {
            return new String[] {"jms:accounting", "jms:production"};
        } else {
            return new String[] {"jms:accounting"};
        }
    }

    private boolean isGoldCustomer(String customer) {
        return customer.equals("honda");
    }
}
