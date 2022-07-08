package camelinaction;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.support.SimpleRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentClientAcknowledge;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcTest extends CamelTestSupport {

    private JdbcTemplate jdbc;

    @BeforeEach
    public void setupDatabase() throws Exception {
        DataSource ds = context.getRegistry().lookupByNameAndType("dataSource", DataSource.class);
        jdbc = new JdbcTemplate(ds);

        jdbc.execute("create table incoming_orders "
            + "( part_name varchar(20), quantity int, customer varchar(20))");
    }

    @AfterEach
    public void dropDatabase() throws Exception {
        jdbc.execute("drop table incoming_orders");
    }
    
    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms", jmsComponentClientAcknowledge(connectionFactory));

        return camelContext;
    }    
    
    protected SimpleRegistry createCamelRegistry() throws Exception {
        SimpleRegistry simple = new SimpleRegistry();
        simple.bind("orderToSql", new OrderToSqlBean());
        
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        ds.setUrl("jdbc:derby:memory:order;create=true");
        ds.setUsername("sa");
        ds.setPassword("");

        simple.bind("dataSource", ds);
        return simple;
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            
            @Override
            public void configure() throws Exception {
                from("jms:accounting")
                    .to("bean:orderToSql")
                    .to("jdbc:dataSource?useHeadersAsParameters=true")
                    .to("mock:result");
            }
        };
    }

    @Test
    public void testJdbcInsert() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);

        int rows = jdbc.queryForObject("select count(*) from incoming_orders", Integer.class);
        assertEquals(0, rows);

        template.sendBody("jms:accounting", "<order name=\"motor\" amount=\"1\" customer=\"honda\"/>");
        
        mock.assertIsSatisfied();

        rows = jdbc.queryForObject("select count(*) from incoming_orders", Integer.class);
        assertEquals(1, rows);
    }

}
