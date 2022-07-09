JpaTest: P221   Does not work as SpringFramework 5 does not support OpenJPA anymore
  *  6.5.2 Persisting objects with the JPA component
  *  Component: jdbc, jpa
  *  DSL: from(), to()
  *  Annotation: N/A
  *  Dependency: camel-core, derby, spring-jdbc, openjpa-persistence-jdbc, camel-jpa, camel-spring
  *  Run: mvn test -Dtest=JpaTest

  Note: Spring Framework 5 does not support openjpa.


To run:
    mvn camel:run
To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test