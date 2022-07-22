* Camel 3 Springboot: Camel Spring Boot BOM
        <groupId>org.apache.camel.springboot</groupId>
        <artifactId>camel-spring-boot-dependencies</artifactId>
        <version>${project.version}</version>
        <type>pom</type>
        <scope>import</scope>

HelloRestController: P258 (Run OK but Error Page when access localhost:8080/spring/hello due to geocoder access permission)
  *  Adding Camel to existing Spring Boot REST endpoint
  *  Annotation: @RestController, @RequestMapping
  *  Dependency: camel-spring-boot-starter, camel-geocoder-starter
  *  In Camel 3, use @EndpointInject(value = "geocoder:address:current") to replace @EndpointInject(uri = "geocoder:address:current")


To run:
    mvn spring-boot:run

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test