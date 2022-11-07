package camelinaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of basic hystrix command in pure Java
 */
public class MyCommandTest {

    @Test
    public void testSuccess() {
        // reset counter
        GlobalState.COUNTER = 0;

        String out = new MyCommand().execute();

        Assertions.assertEquals("Count 1", out);
//        Assert.assertEquals("Count 1", out);
    }

    @Test
    public void testFailure() {
        // reset counter
        GlobalState.COUNTER = 0;

        // the first 4 should be okay
        Assertions.assertEquals("Count 1", new MyCommand().execute());
        Assertions.assertEquals("Count 2", new MyCommand().execute());
        Assertions.assertEquals("Count 3", new MyCommand().execute());
        Assertions.assertEquals("Count 4", new MyCommand().execute());

        // this should fail
        try {
            Assertions.assertEquals("Count 5", new MyCommand().execute());
            Assertions.fail("Should throw exception");
        } catch (Exception e) {
            // expected
        }

    }

    @Test
    public void testOnlyOneCallPerCommand() {
        // reset counter
        GlobalState.COUNTER = 0;

        MyCommand myCommand = new MyCommand();
        String out = myCommand.execute();
        Assertions.assertEquals("Count 1", out);

        try {
            // you cannot call a 2nd time on the same instance, you must create a new instance per call
            String out2 = myCommand.execute();
            Assertions.assertEquals("Count 2", out2);
            Assertions.fail("Should throw exception");

        } catch (Exception e) {
            // expected
            // com.netflix.hystrix.exception.HystrixRuntimeException: MyCommand command executed multiple times - this is not permitted.
        }
    }
}
