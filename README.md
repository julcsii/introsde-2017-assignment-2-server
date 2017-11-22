# introsde-2017-assignment-2-server
University of Trento

Introduction of Service Design Engineering 

2017 Fall

#### Name: Julia Hermann
#### Email: julia.hermann@studenti.unitn.it
#### Client code: https://github.com/julcsii/introsde-2017-assignment-2-client
#### Server base URL on Heroku: https://sde-assignment-2.herokuapp.com/

#### Requirements
The system is designed to be used by Universities who want the students to register as Users and every User has a list of preferred activities. The Users have first name, last name and birth date. The activities have name, description, type, place and start date. In my understanding every user can have multiple activities, but an activity can only be linked to one person. (Every activity done by a person is unique. However it can by chance have the same attributes as another person's activity.)

##### Model
Users are represented with Person.java class, Activities are represented with Activities.java class. They both define tables in the database using annotations of the Java Persistence API. The following activity types are supported: SPORT, SOCIAL, ACADEMIC, WORK, CULTURE. Activity types are not stored in the database, but implemented with the ActivityType.java enum class. 

#### Implementation
SQLite database is used via JDBC driver and EclipseLink JPA implementation. The tables are filled with GET {base_url}/database_init HTTP request. The server is deployed on Heroku.

The Jersey server implements the following RESTful web-services:

Request#0: GET /database_init: an initialization process at deployment
Request#1: GET /person: lists all the people
Request#2: GET /person/{idPerson}: gives all the personal information plus related information
Request#3: PUT /person/{idPerson}: updates the personal information of the person identified by {idPerson} - every attribute of the person has to be given to the request, otherwise information will be lost
Request#4: POST /person: creates a new person and return the newly created person with its assigned id
Request#5: DELETE /person/{idPerson}: deletes the person identified by {id} from the system
Request#6: GET /activity_types: returns the list of activity_types
Request#7: GET /person/{idPerson}/{activity_type}: returns the list of activities of {activity_type} (e.g. Social) for person identified by {idPerson}
Request#8: GET /person/{idPerson}/{activity_type}/{idActivity}: returns the activities of {activity_type} (e.g. Social) identified by {idActivity} for person identified by {idPerson}
Request#9: POST /person/{idPerson}/{activity_type}: saves a new value for the {activity_type} (e.g. Social) of person identified by {idPerson}
Request#10 (Extra #1): PUT /person/{idPerson}/{activity_type}/{idActivity}: updates the value for the {activity_type} (e.g., Social) identified by {idActivity}, related to the person identified by {idPerson} -  - every attribute of the person has to be given to the request, otherwise information will be lost
Request#11 (Extra #2): GET /person/{idPerson}/{activity_type}?before={beforeDate}&after={afterDate}: returns the activities of {activity_type} (e.g., Social) for person {idPerson} which {start_date} is in the specified range of date (Date format should be: "yyyy-MM-dd'T'HH:mm:ss")


#### Code execution locally with Eclipse
1. Clone repository
2. Create new Dynamic Web Project referring the location where you put the source code
3. Add Ivy Library
4. Compile with build.xml
5. Run App.java
(6. To fill database, run DatabaseInit.java or send GET /database_init request)


#### Notes
Activity types have to be written with capital letters.
The server supports both XML and JSON representation.

Request#8, Request#10: activity_type is redundant when idActivity is given.

The tables are filled with GET {base_url}/database_init HTTP request.


