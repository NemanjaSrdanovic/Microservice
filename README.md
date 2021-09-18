# Microservice Implementation

## Project description

The goal is the development of an microservice that is made available to all fellow students and colleagues 
at the DKE using the OLIVE microservice framework on the DKE server. Our microservice returns "Points of Interest" 
based on your current address and a selected category. The microservice should receive a place as input 
and return the categories of the places in a given radius of the area. Than a search for a specific category can be made,
which then outputs the exact locations and makes them available to the user.



## Project implemetation

Development of a new microservice in the OLIVE framework is separated in two sub-tasks:


### Sub-task 1 - Conception 

	
<details>
<summary>Design thinking and modelling</summary>
<p>
Development and presentation of an exposé which represents the microservice. In addition, potential applications of microservice
are listed and detailed, and possible combinations with microservices from other groups are considered. Furthermore, the implementation 
of the service is planned and individual components of the application are modeled. In addition, APIs that are required for the implementation
of the microservices are discovered and described.
</p>
</details>



### Sub-task 2 - Implementation and deployment

	
<details>
<summary>Development and testing</summary>
<p>
The microservice is developed with Java and the Spring Boot framework. For this purpose, several REST service endpoints are defined 
via which requests / data are transferred to the microservice and results are returned.

For this purpose, data that is transferred to the microservice is converted into JSON and corresponding requests are sent to several 
APIs that provide us with raw data, which are processed and sent back to the requestor.


In addition, the microservice is structured in such a way that each request has its own identifier, so that subsequent requests to the first 
request can be processed locally from the existing data and no new requests have to be sent to the APIs.

Every change to the application is checked for correctness with the help of JUnit tests, which are running automatically.

</p>
</details>

## Project technology

**Programming Language** : Java <br/>
**Application Framework** : Spring Boot <br/>
**Microservice Framework** : OLIVE <br/>
**Build tool** : Maven <br/>
**Frontend Framework** : Vue.js <br/>
**Containerization**: Docker <br/>
**Lifecycle Tool**: GitLab <br/>
