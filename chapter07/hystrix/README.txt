MyCommandTest: P283
    * Hystrix provides the HystrixCommand class which you extend and implement the run method
      containing your potential faulty code, such as a remote network call.

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test -Dtest=MyCommandTest
    mvn test -Dtest=MyCommandWithFallbackTest
