package camelinaction;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.io.IOException;

import static camelinaction.GlobalState.COUNTER;

/**
 * Our custom command that is wrapped in Hystrix
 * Created our custom Hystrix command in the MyCommand class
 * extending HystrixCommand<String> with String specified as type which then corresponds
 * to the return type of the run method.
 * In this example we have built in the code a failure that happens for
 * every 5th call to simulate some kind of error.
 */
public class MyCommand extends HystrixCommand<String> {

    public MyCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
    }

    @Override
    protected String run() throws Exception {
        // use a shared state as counter
        // as you cannot store any state in HystrixCommand
        COUNTER++;
        if (COUNTER % 5 == 0) {
            throw new IOException("Forced error");
        }
        return "Count " + COUNTER;
    }
}
