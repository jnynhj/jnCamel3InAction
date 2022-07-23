Copy from:
    https://medium.com/@naskavinda/building-spring-boot-rest-api-using-apache-camel-and-postgresql-ead210c92503


Note:
 *   Check   http://localhost:8080/rest/hello
            http://localhost:8080/rest/api-doc
    - camel-spring-boot-dependencies 3.9.0 works
    - camel-spring-boot-dependencies 3.10.0 to 3.18.0 not working, not sure why?
        Whitelabel Error Page

 * Unlike cart-camel module, this module use springboot camel with tomcat on port 8080.
    http://localhost:8080/api/api-doc
     - Not working
    (Remove .type(CartDto.class) and .type(CartDto[].class will work)

    http://localhost:8080/api/cart?sessionid=123 (POST)
    {
     "itemId":"444",
     "quantity": 57
    }

      - Not working.