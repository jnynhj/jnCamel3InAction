JsonExpressionTest: P143
  *  4.6.2 Using beans as expressions in routes
     - Use recipientList() for single or multiple destination, more powerful than toD()
  *  EIP: Recipient List Pattern.
  *  Run: mvn test -Dtest=JsonExpressionTest

  *  DSL: recipientList(), setHeader(), method(), simple()
  *  Annotation: @JsonPath
  *  Dependency: camel-jsonpath

JsonToDExpressionTest: P144
  *  Using toD as dynamic to (a single dynamic computed destination), whereas Recipient List is a much more elaborate
     EIP pattern.
  *  DSL: toD(), setHeader(), method()
  *  Run: mvn test -Dtest=JsonToDExpressionTest


To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test