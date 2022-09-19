rules-camel-springboot: P273
  *  Use Camel Route with Spring Boot to create REST service
  *  Annotation: @Component
  *  DSL: rest, get, restConfiguration
  *  Dependency: camel-spring-boot-starter, camel-servlet-starter, spring-boot-starter-web
  *  URL: http://localhost:8181/api/rules/123,456
     Camel by default will use context-path /camel/*, which can be reconfigured in the application.properties or mapping.addUrlMappings().
  *  Actuator URL: http://localhost:8080/actuator/health

To run:
    mvn spring-boot:run

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test

IVP:
    * Use browser to check below URL:
        http://localhost:8383/api/ratings/123,456

Running the Rules and Backend services
You can run these services from the chapter7/prototype directory. First you need to start the
backend service:
cd backend
mvn compile exec:java
... and then from another shell you can start the rules microservice:
cd rules
mvn spring-boot:run
.. and then from a web browser you can call the rules service using the following url:
http://localhost:8181/api/rules/123,456
The backend has been hardcoded to return three different items using ids 123, 456 and 789,
therefore you can try urls such as:
http://localhost:8181/api/rules/123
http://localhost:8181/api/rules/456,789


Issue: Exception connecting to tcp://localhost:61616