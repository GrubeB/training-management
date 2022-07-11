## Technologies
* Spring Boot 2.7.1
* Liquibase 
* Thymeleaf
* MySQL
* Junit and Mockito

## Prerequisite

1. Java 11
2. MySQL Server

## Run

1. Setup dev database:

   App dosent have configured Dockerfiles, so user has to create manually dwo databases: 'db_training' and 'db_training_test'. Both on localhost on port 3306, with the user and password 'root'.


2. Build everything:

         .\mvnw.cmd clean install

3. Start application:

          .\mvnw.cmd spring-boot:run


## Build

1. Rebuild everything:

        .\mvnw.cmd clean compile


2. Build without executing tests:

         .\mvnw.cmd clean install -DskipTests

## Test

1. Execute unit and integration tests:

       .\mvnw.cmd clean test



