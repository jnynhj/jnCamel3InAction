MyCommandTest: P283
    * In the route we use Hystrix to denote the beginning of the protection of Hystrix. Any of the
      following nodes are run from within a HystrixCommand. In this example that would be calling
      the counter bean. To denote when the Hystrix command ends we use end() in Java DSL
      which means the log node is run outside Hystrix.
    * Camel 3 use DSL circuitBreaker() instead of hystrix().

CamelHystrixTimeoutTest:
    * Use resillience4j, or microprofile-fault-tolerance for configuring the circuit breaker EIP.
      (replace hystrixConfiguration in Camel 3)
    * This test failed (need to fix)

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test -Dtest=CamelHystrixTest
    mvn test -Dtest=CamelHystrixWithFallbackTest
    mvn test -Dtest=CamelHystrixTimeoutTest
    mvn test -Dtest=CamelHystrixTimeoutAndFallbackTest
