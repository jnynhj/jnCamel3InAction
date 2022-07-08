OrderRouter: P208 Not working
  *  6.3.1 Sending and receiving messages
  *  Component: file:src/data, jms:topic:xmlOrders
  *  DSL: from(), to(), choice(), when()
  *  Annotation: N/A
  *  Dependency: camel-core, camel-spring-main
  *  Run: mvn camel:run
        Error:  class path resource [META-INF/spring/] cannot be resolved to URL because it does not exist

RequestReplyJmsTest: P210
  *  6.3.2 Request-reply messaging
  *  Component: jms
  *  DSL: inOut()
  *  Dependency: camel-language
  *  Run:
        mvn test -Dtest=RequestReplyJmsTest

To run:
    mvn camel:run
To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test