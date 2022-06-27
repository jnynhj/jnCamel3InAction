OrderToCsvProcessorTest: P80
  * Data format transformation using Processor interface
    - DSL: process()

OrderToCsvBeanTest: P81
   * Data format transformation using POJO class
     - DSL: bean()

TransformTest: P82
  * Use transform() DSL with regex expression
  * DSL: tranform(body().regexRepaceAll(...))

TransformExpressionTest: P82
  * Use transform() DSL with inline Camel Expression that allows you to use Java code in its evaluate() method.
    - DSL: tranform(new Expression())

To run a test class:
        mvn test -Dtest=<testClassName>

To run all tests:
    mvn test