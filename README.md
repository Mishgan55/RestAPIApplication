# RestAPIApplication
Pet Project
I created a REST API service that receives data from a "sensor". Since I don't have a real sensor, RestClient will act as a sensor.
That is, a server with a Spring REST API application will run on the computer,
and the computer will send HTTP requests to the Spring application as if it were a "sensor".
## Technologies used:
Spring REST,Spring MVC, Spring Boot, Hibernate, JPA,
PostgreSQL, Maven.
## Links
1) Sensor registration
POST http://localhost:8080/sensors/registration
2) Adding a measurement from a sensor
POST http://localhost:8080/measurements/add
3) Getting all measurements
GET http://localhost:8080/measurements
4) Getting the number of rainy days
GET http://localhost:8080/measurements/rainyDaysCount
