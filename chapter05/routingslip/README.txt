Others: p182
  *  Additional configuration options for the Aggregator EIP:
        mvn test -Dtest=RoutingSlipSimpleTest
        mvn test -Dtest=SpringRoutingSlipSimpleTest
        mvn test -Dtest=RoutingSlipHeaderTest
        mvn test -Dtest=SpringRoutingSlipHeaderTest
        mvn test -Dtest=RoutingSlipTest
        mvn test -Dtest=SpringRoutingSlipTest
        mvn test -Dtest=RoutingSlipBeanTest
        mvn test -Dtest=SpringRoutingSlipBeanTest

RoutingSlipSimpleTest: P179
  *  This example assumes that the incoming message contains the slip in the header with the key
     mySlip. This example expects a preexisting header containing the routing slip.
  *  EIP: Routing Slip EIP
  *  DSL: routingSlip(), header()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=RoutingSlipSimpleTest

RoutingSlipHeaderTest: p180
  *  Using a bean to compute the routing slip header. In the Java DSL, you can use the method call expression to
     invoke the bean and set the header.
  *  EIP: Routing Slip EIP
  *  DSL: routingSlip(), setHeader().method()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=RoutingSlipHeaderTest

RoutingSlipTest: p180
  *  Instead of using a header expression, you can use any other Expression to generate the routing slip.
  *  EIP: Routing Slip EIP
  *  DSL: routingSlip().method()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=RoutingSlipTest

RoutingSlipBeanTest: p180
  *  Use beans and annotations.
     The @RoutingSlip annotation allows you to turn a regular bean method into the Routing Slip EIP.
  *  EIP: Routing Slip EIP
  *  DSL: routingSlip().method()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=RoutingSlipBeanTest


To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test