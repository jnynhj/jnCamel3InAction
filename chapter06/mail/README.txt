MailTest: P236
  *  6.5.1 Accessing data with the JDBC component
  *  Component: imap, smtp
  *  DSL: from(), to()
  *  Annotation: N/A
  *  Dependency: camel-mail, mock-javamail, javax.mail (com.sun.mail)
  *  Run: mvn test -Dtest=MailTest


To run:
    mvn camel:run
To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test