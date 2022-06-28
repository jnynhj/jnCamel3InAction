OrderToCsvProcessorTest: P80
  * Data format transformation using Processor interface
  *  DSL: process()

OrderToCsvBeanTest: P81
   * Data format transformation using POJO class
   * DSL: bean()

TransformTest: P82
  * Use transform() DSL with regex expression
  * DSL: tranform(body().regexRepaceAll(...))

TransformExpressionTest: P82
  * Use transform() DSL with inline Camel Expression that allows you to use Java code in its evaluate() method.
  * DSL: tranform(new Expression())

PurchaseOrderJaxbTest: P91
  * Transforming using JAXB
  * Dependency: camel-jaxb
  * DSL: marshal(), unmarshal()

PurchaseOrderCsvTest: P94
  * The camel-csv data format is capable of transforming to and from CSV format.
  * consume CSV files, split out each row, and send it to a JMS queue.
  * Dependency: camel-csv
  * DSL: unmarshal(), csv(), split()

PurchaseOrderBindyTest, PurchaseOrderUnmarshalBindyTest: P97
  * Using Camel’s Bindy data format. By annotating the model object with camel-bindy annotations, you can easily
    transform messages between CSV and Java model objects.
  * Dependency: camel-bindy
  * DSL: marshal(), unmarshall(), bindy()

PurchaseOrderJSONTest: p98
  * 3.4.4 Using Camel’s JSON data format
  * Dependency: camel-support, camel-jackson
  * DSL: bean(), marshal(), json()

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test