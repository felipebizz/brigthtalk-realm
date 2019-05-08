# Brigthtalk-Realm
Java web-app using Spring RESTful MVC
 that store data in a relational database, and can be deployable to Tomcat.
 
 It is possible create a new Realm by API using a content-ype: JSON or XML.
 

[![Build Status](https://travis-ci.org/felipebizz/brigthtalk-realm.svg?branch=master)](https://travis-ci.org/felipebizz/brigthtalk-realm)


#### Used Tecnologies

* Java 8
* spring-webmvc
* hibernate
* Jackson API for JSON/XML
* Log4j
* Tomcat 7
* MySQL
* Orika

**How to run:**
-----
First of all create a database called **BRIGHTTALK** at mySQL.

* Clone project
    
        cd brightalk-realm
    
    ```mvn clean install```
   
* Starting Server

    ```mvn tomcat7:run```
    
## Endpoints Available
    
```
http://localhost:8080/service/user/realm/create
http://localhost:8080/service/user/realm/all
http://localhost:8080/service/user/realm/{id}

