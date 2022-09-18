package camelinaction;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

import javax.jms.ConnectionFactory;

public class InventoryApplication {
    /**
     * Main class to run this example, such as from your Java editor
     */
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("vm://localhost:61616?broker.persistent=false");

        main.bind("jms", new ActiveMQConnectionFactory("vm://localhost:61616?broker.persistent=false"));
        main.bind("inventory", new InventoryService());
        main.configure().addRoutesBuilder(new InventoryRoute());
//        main.getCamelContext().addComponent("jms",
//                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        main.run();

    }
}
