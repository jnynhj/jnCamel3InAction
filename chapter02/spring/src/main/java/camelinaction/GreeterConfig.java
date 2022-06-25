package camelinaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreeterConfig {
    @Bean
    public Greeter myGreeter() {
        return new EnglishGreeter();
    }

    @Bean
    public GreetMeBean greetMeBean() {
        GreetMeBean gmbean = new GreetMeBean();
        gmbean.setGreeter(myGreeter());
        return gmbean;
    }
}
