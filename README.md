# omid_exercise
This is a catalog application.
## Run tests And Build package
```
windows: mvnw clean package
linux: ./mvnw clean package 
```
## Run package
The package created in the target folder
```
java -jar catalog-1.0-SNAPSHOT.jar
```
## Logging
The Logs save in the catalogApp.log file

## Users
There are two types of users. ADMIN and USER. Both of them can call private rest endpints, but ADMIN can call private user management rest endpoints too. 
Everyone can see public services. for more information about rest endpoitns, see the rest controller classes, please.

default user with type ADMIN For basic auth (Postman is suggested)
```
user:admin
password:password
```

## Headers
Accept:application/json or application/xml 

Content-Type:application/json
