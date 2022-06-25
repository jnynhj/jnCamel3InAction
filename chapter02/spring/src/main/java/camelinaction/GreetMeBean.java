package camelinaction;


public class GreetMeBean {

    private Greeter greeter;

    public void setGreeter(Greeter greeter) {
        this.greeter = greeter;
    }

    public void execute() {
        System.out.println(greeter.sayHello());
    }
}

