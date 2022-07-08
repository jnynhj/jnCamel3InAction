NettyTcpTest: P215
  *  6.4.1 Using Netty for network programming
  *  Component: netty, jms
  *  DSL: from(), to()
  *  Annotation: N/A
  *  Dependency: camel-core, camel-netty
  *  Run: mvn test -Dtest=NettyTcpTest

NettyCustomCodecTest: P217
  *  6.4.2 Using custom codecs
  *  Component: netty, jms
  *  DSL: inOut()
  *  Dependency: camel-core, camel-netty
  *  Run:
        mvn test -Dtest=NettyCustomCodecTest

To run:
    mvn camel:run
To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test