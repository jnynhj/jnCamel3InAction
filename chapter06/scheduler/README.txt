SchedulerTest: P231
  *  6.7.1 Using the Scheduler component
  *  Component: scheduler, stream
  *  DSL: from(), to()
  *  Annotation: N/A
  *  Dependency: camel-stream
  *  Run: mvn test -Dtest=SchedulerTest

QuartzTest, QuartzCronTest: P233
  *  6.7.2 Enterprise scheduling with Quartz
  *  Component: quartz, stream
  *  DSL: from(), to()
  *  Annotation: N/A
  *  Dependency: camel-quartz, camel-stream
  *  Run: mvn test -Dtest=QuartzTest
          mvn test -Dtest=QuartzCronTest

  Note: Camel 3 use camel-quartz, and component URL is quartz://.


To run:
    mvn camel:run
To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test