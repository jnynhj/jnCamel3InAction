SplitterABCTest: P168
  *  Using the Splitter EIP to split 1 msg to 3.
  *  EIP: Splitter EIP
  *  DSL: split(body())
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=SplitterABCTest

SplitterBeanTest: P171
  *  Using method on a Bean with the Splitter EIP to split msg.
  *  EIP: Splitter EIP
  *  DSL: split().method(<Type>, "<method>")
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=SplitterBeanTest

SplitterAggregateABCTest: P175
  *  Split and aggregate messages again. The Camel Splitter provides a built-in aggregator.
  *  EIP: Composed Message Processor EIP, Splitter EIP
  *  DSL: split(body(), <AggregationStrategy>), bean()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=SplitterAggregateABCTest

SplitterStopOnExceptionABCTest: P176
  *  Handling error(exception) when split and aggregate messages again. The Camel Splitter provides a built-in aggregator.
  *  EIP: Composed Message Processor EIP, Splitter EIP
  *  DSL: split(body(), <AggregationStrategy>), bean(), stopOnException()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=SplitterStopOnExceptionABCTest

SplitterAggregateExceptionABCTest: P178
  *  Handling error(exception) by ignoring it when split and aggregate messages again. The Camel Splitter provides a built-in aggregator.
  *  EIP: Composed Message Processor EIP, Splitter EIP
  *  DSL: split(body(), <AggregationStrategy>), bean()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=SplitterAggregateExceptionABCTest

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test