# Microservices-Architecture
Microservices architecture with **Spring Boot and Spring Cloud technologies**

This repository is related to microservices architecture using spring boot and cloud technologies. Below microservices are related to e-commerce with very neat and clean architecture:

## 1. [common-lib](https://github.com/ShyamJiPatel/Microservcies-Architecture/tree/master/common-lib "Common Library")
This is the common library used in all microservices, it is having the functionality like Base Classes, Logging configuration, Global Exception handling and some utilities.
## 2. [discovery-server](https://github.com/ShyamJiPatel/Microservcies-Architecture/tree/master/discovery-server "Discovery Microservice")
All the microservies are registered here so they can communicate with each other without knowing the actual adderss (IP and Port) of any microservices.
## 3. [productservice](https://github.com/ShyamJiPatel/Microservcies-Architecture/tree/master/productservice "Product Microservice")
This microservice is related to Products and their Category/Subcategory. User can search the projects based on the categories and seller can upload the project their images.
## 4. [pushnotification](https://github.com/ShyamJiPatel/Microservcies-Architecture/tree/master/pushnotification "Push Notification Microservice")
This microservice sends the push notification to Android/iOS devices.
## 5. [user-service](https://github.com/ShyamJiPatel/Microservcies-Architecture/tree/master/user-service "User Microservice")
This microservice is responsible for Signup/Login for the user. Role and permissions can also be tagged to user using this microservice.
## 6. [zuul-service](https://github.com/ShyamJiPatel/Microservcies-Architecture/tree/master/zuul-server "Zuul Microservices")
This is the Gateway for all the microservcies so we do not need to remember the address (IP/Port) of individual microservices.



