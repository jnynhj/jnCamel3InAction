HelloCamel: P246
  *  7.2.1 Running Camel standalone using Camel Main class
  *  Component: jetty
  *  DSL: from(), to(), transform(), simpel()
  *  Annotation: N/A
  *  Dependency: camel-jetty
  *  Run: mvn compile exec:java -Pmanual

HelloMain: P246
  *  7.2.1 Running Camel standalone using Camel Main class
  *  Component: jetty
  *  DSL: from(), to(), transform(), simple()
  *  Annotation: N/A
  *  Dependency: camel-jetty, camel-main
  *  Notes:
     1. Add camel-main dependence for org.apache.camel.main.Main
     2. Add routes to Main object:
        Camel 3: main.configure().addRoutesBuilder(new HelloRoute());
        Camel 2: main.addRouteBuilder(new HelloRoute());
  *  Run: mvn compile exec:java -Pmain


To run:
    mvn camel:run
To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test