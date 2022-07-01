package camelinaction;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.processor.PredicateValidationException;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.apache.camel.builder.PredicateBuilder.not;
import static org.apache.camel.test.junit5.TestSupport.assertIsInstanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompoundPredicateTest extends CamelTestSupport {

    /**
     * This test should pass
     */
    @Test
    public void testCompoundPredicateValid() throws Exception {
        getMockEndpoint("mock:valid").expectedMessageCount(1);

        String xml = "<book><title>Camel in Action</title><user>Donald Duck</user></book>";
        template.sendBodyAndHeader("direct:start", xml, "source", "batch");

        assertMockEndpointsSatisfied();
    }

    /**
     * We expect this test to fail with an exception. But want to let Camel print the exception on the console
     * so you can see the exception message, and Camel printing the compound predicate that failed
     */
//    @Test(expected = PredicateValidationException.class)
    @Test
    public void testCompoundPredicateInvalid() {
        String xml = "<book><title>Camel in Action</title><user>Claus</user></book>";

        CamelExecutionException thrown = Assertions.assertThrows(CamelExecutionException.class, () -> {
            template.sendBodyAndHeader("direct:start", xml, "source", "batch");
        });
        System.out.println("JNDBG: " + thrown.getMessage());
        System.out.println("JNDBG: " + thrown.getCause());
        assertTrue(thrown.getMessage().contains("Exception occurred during execution on the exchange"));
        assertIsInstanceOf(PredicateValidationException.class, thrown.getCause());
    }

    public static boolean isAuthor(String xml) {
        return xml.contains("Claus") || xml.contains("Jonathan");
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // build a compound predicate using the PredicateBuilder
                Predicate valid = PredicateBuilder.and(
                        // this xpath must return true
                        xpath("/book/title = 'Camel in Action'"),
                        // this simple must return true
                        simple("${header.source} == 'batch'"),
                        // this method call predicate must return false (as we use not)
                        not(method(CompoundPredicateTest.class, "isAuthor")));

                // use the predicate in the route using the validate eip
                from("direct:start")
                    .validate(valid)
                    .to("mock:valid");
            }
        };
    }
}
