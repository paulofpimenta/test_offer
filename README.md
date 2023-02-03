# Welcome to the UserAPI demo application

## 1. Installation

The application is based on SpringBoot framework, shipped with an embedded MongoDB database

To install dependencies for this demo application, run the following command : 

```
./mvnw clean install
```
If you want to skip tests, then run :

```
./mvnw clean install -Dmaven.test.skip
```

Both commands will generate a deployable file (.jar) on the target folder. 
To run the application, execute the following command :  

```
./mvnw spring-boot:run
```

The application runs at : 

```
http://localhost:8080/api/v1/
```

and an OpenAPI to document and test the entry points will be available at : 
```
http://localhost:8080/api/v1/swagger-ui/index.html
```

If you wish to check on created users, the application's embedded MongoDB is set to listen on default port `27017`. 
