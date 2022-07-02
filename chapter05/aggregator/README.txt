AggregateABCTest: P150
  *  Using the Aggregator EIP to collect any three messages and combine them.
  *  EIP: Aggregator EIP
  *  DSL: aggregate(), header(), completionSize()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=AggregateABCTest

AggregateXMLTest: P154
  *  The Aggregator EIP using multiple completion conditions.
     -  ${property.xxx} is deprecated in 3.x. Use ${exchangeProperty.xxx} which is the correct name for the function.
  *  EIP: Aggregator EIP
  *  DSL: aggregate(), xpath(), completionSize(), complettionTimeout()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=AggregateXMLTest

Others: p157
  *  Additional configuration options for the Aggregator EIP:
        mvn test -Dtest=AggregateABCEagerTest
        mvn test -Dtest=SpringAggregateABCEagerTest
        mvn test -Dtest=AggregateABCCloseTest
        mvn test -Dtest=SpringAggregateABCCloseTest
        mvn test -Dtest=AggregateABCInvalidTest
        mvn test -Dtest=SpringAggregateABCInvalidTest
        mvn test -Dtest=AggregateABCGroupTest
        mvn test -Dtest=SpringAggregateABCGroupTest
        mvn test -Dtest=AggregateTimeoutThreadpoolTest
        mvn test -Dtest=SpringAggregateTimeoutThreadpoolTest

AggregatePojoTest: P159
  *  Using POJO for the Aggregator EIP.
  *  EIP: Aggregator EIP
  *  DSL: aggregate(), header(), completionSize(), AggregationStrategies.bean()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=AggregatePojoTest

AggregateABCLevelDBTest: P1161
  *  Using LevelDB for the Aggregator EIP.
  *  EIP: Aggregator EIP
  *  DSL: aggregate(), completionSize(), AggregationRepository()
  *  Annotation: N/A
  *  Dependency: camel-leveldb
  *  Run: mvn test -Dtest=AggregateABCLevelDBTest

AggregateABCRecoverTest: P1164
  *  Using recovery for the Aggregator EIP.
  *  EIP: Aggregator EIP
  *  DSL: aggregate(), completionSize(), AggregationRepository()
          header(Exchange.REDELIVERED), header(Exchange.REDELIVERY_COUNTER)
          LevelDBAggregationRepository(): RecoverableAggregationRepository()
  *  Annotation: N/A
  *  Dependency: camel-leveldb
  *  Run: mvn test -Dtest=AggregateABCRecoverTest


To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test