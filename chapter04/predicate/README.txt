JsonPredicateTest: P140
  *  Using a bean with methods as a predicate in Camel
  *  Camel implementation of Content-based router (CBR) Pattern.
     - Run mvn test -Dtest=JsonPredicateTest

  *  DSL: method()
  *  Annotation: @JsonPath
  *  Dependency: camel-jsonpath

CompoundPredicateTest: P141
  *  Using compound predicates in Java: XPath, Simple, and Bean.
  *  DSL: method(), xpath(), simple()


To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test