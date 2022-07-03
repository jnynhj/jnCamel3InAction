LoadBalancerTest: p186
  *  The Camel Load Balancer EIP is a processor that implements the org.apache.camel.
     processor.loadbalancer.LoadBalancer interface. The LoadBalancer offers methods to add
     and remove processors that should participate in the load balancing.
     When using the Load Balancer EIP, you have to select a balancing strategy. A common and
     understandable strategy is to take turns among the services; this is known as the round-robin
     strategy.

  *  Load-balancing strategies: Chooses a processor in a round-robin fashion, which spreads the load evenly.
     This is a classic and well-known strategy.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().roundRobin()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=LoadBalancerTest

RandomLoadBalancerTest: P187
  *  Load-balancing strategies: Chooses a processor randomly.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().random()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=RandomLoadBalancerTest

StickyLoadBalancerTest: P187
  *  Load-balancing strategies: Uses an expression to calculate a correlation key that dictates the processor chosen.
     You can think of this as the session ID used in HTTP requests.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().sticky(header())
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=StickyLoadBalancerTest

CircuitBreakerLoadBalancerTest: P187 (Deprecated, use Hystrix EIP instead which is the popular Netflix implementation of circuit breaker instead)
  *  Load-balancing strategies: Circuit Breaker.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().circuitBreaker()
  *  Annotation: N/A
  *  Dependency: camel-core

TopicLoadBalancerTest: P187
  *  Load-balancing strategies: Topic.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().topic()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=TopicLoadBalancerTest

FailoverLoadBalancerTest: P187
  *  Load-balancing strategies: Failover.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().failover()
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=FailoverLoadBalancerTest

FailoverRoundRobinLoadBalancerTest: P190
  *  Load-balancing strategies: Failover with round-robin without enabling Camel error handling (inheritErrorHandler=false).
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().failover(maximumFailoverAttempts, inheritErrorHandler, roundRobin)
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=FailoverRoundRobinLoadBalancerTest

FailoverInheritErrorHandlerLoadBalancerTest: P190
  *  Load-balancing strategies: Failover with round-robin by enabling Camel error handling (inheritErrorHandler=true).
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance().failover(maximumFailoverAttempts, inheritErrorHandler, roundRobin)
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=FailoverInheritErrorHandlerLoadBalancerTest

CustomLoadBalancerTest: P192
  *  Load-balancing strategies: Using a custom load balancer.
  *  EIP: Load Balancer EIP
  *  DSL: loadBalance(new CustomerLB())
  *  Annotation: N/A
  *  Dependency: camel-core
  *  Run: mvn test -Dtest=CustomLoadBalancerTest


To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test