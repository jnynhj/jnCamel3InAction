RatingRoute: P273
  *  Use Camel Route with Spring Boot to create REST service
  *  Annotation: @Component
  *  DSL: rest, get, restConfiguration
  *  Dependency: camel-spring-boot-starter, camel-servlet-starter, spring-boot-starter-web
  *  URL: http://localhost:8383/api/ratings/123,456
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