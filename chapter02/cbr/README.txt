OrderRouterTest:
  * Implement cbr and filter TCs in chapter2
    - CBR EIP: choice(), when(predicate), otherwise(), end(), stop()
    - Filter EIP: filter
    - Multicast and parallelProcessing: multicast(), parallelProcessing()

  * To run
        mvn test -Dtest=OrderRouterTest

OrderRouterWithMulticastSOETest:
  * Stopping the multicast on exceptiona: stopOnException(), throwException()

OrderRouterWithRecipientListTest:
  * Using recipient list
    - recipientList()

OrderRouterWithRecipientListAnnotationTest:
  * Using recipient list
    - @recipientList on POJO, @XPath bean-binding annotation, and bean() DSL

OrderRouterWithWireTapTest:
  * wireTap()

To run all tests:
    mvn test