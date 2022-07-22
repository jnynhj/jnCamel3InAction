* Camel 3 Springboot: Camel Spring Boot BOM
        <groupId>org.apache.camel.springboot</groupId>
        <artifactId>camel-spring-boot-dependencies</artifactId>
        <version>${project.version}</version>
        <type>pom</type>
        <scope>import</scope>

HelloRoute: P260 (Run OK but Error Page when access due to geocoder access permission)
    Error: java.lang.IllegalStateException: Must provide either API key or Maps for Work credentials.

  *  Use Camel Route with Spring Boot to create REST service
  *  Annotation: @Component
  *  DSL: rest, produce, get, transform
  *  Dependency: camel-spring-boot-starter, camel-servlet-starter, camel-geocoder-starter
  *  URL: http://localhost:8080/camel/hello
     Camel by default will use context-path /camel/*, which can be reconfigured in the application.properties.

To run:
    mvn spring-boot:run

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test