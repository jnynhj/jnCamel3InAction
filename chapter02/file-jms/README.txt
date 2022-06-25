FtpToJMSWithProcessorExample:
    Configured to use embedded ActiveMQ.

    To run FtpToJMSWithProcessorExample
        mvn exec:java
    or
        mvn exec:java -Dexec.mainClass="camelinaction.FtpToJMSWithProcessorExample"

FtpToJMSWithDynamicToTest
  * Need active-camel dependency
    To run FtpToJMSWithDynamicToTest
        mvn test -Dtest=FtpToJMSWithDynamicToTest

SimpleMockTest:
    Test camel-test-junit5

FtpToJMSWithPropertyPlaceholderTest:
  * Need camel-properties dependency
  * To run
        mvn test -Dtest=FtpToJMSWithPropertyPlaceholderTest

To run all tests:
    mvn test