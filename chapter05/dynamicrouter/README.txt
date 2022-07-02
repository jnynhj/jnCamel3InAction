DynamicRouterTest: p183
  *  Using the Dynamic Router: Camel keep invoking the route method until it returns null.
  *  EIP: Dynamic Router EIP
  *  DSL: dynamicRouter(method()), Exchange.SLIP_ENDPOINT, language://simple:*
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=DynamicRouterTest

DynamicRouterAnnotationTest: p184
  *  Using @DynamicRouter annotation to invoke the route method on the bean.
     WARNING When using @DynamicRouter, it’s important to not use dynamicRouter in the DSL at the
     same time.
  *  EIP: Dynamic Router EIP
  *  DSL: bean(), @Header(Exchange.SLIP_ENDPOINT), language://simple:*
  *  Annotation: @DynamicRouter
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=DynamicRouterAnnotationTest

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test