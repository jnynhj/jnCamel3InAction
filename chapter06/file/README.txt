FilePrinterTest: P201
  *  This route will read files from the data/inbox directory and print the contents of each to the console.
  *  Component: file:data/inbox, stream:out
  *  DSL: from(), to()
  *  Annotation: N/A
  *  Dependency: camel-core, camel-stream
  *  Run: mvn test -Dtest=FilePrinterTest


To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test