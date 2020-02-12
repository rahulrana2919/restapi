# restapi
Rest API for Person entities using Springboot and Spring Security(Jwt)

Description: SpringBoot has been used to implement rest api for person entities and these are secured using SpringSecurity(Jwt).
H2 database has been used as in-memory database.

Run the project
1. Project need to be cloned OR imported as maven project in intellij and run using the main method in com.rahrana.restapi.App file.
2. Using REST client e.g postman, hit the requests at below URL

OR

Find the API documentation at swagger UI: http://localhost:8080/swagger-ui.html
. And try below requests from there.

--------------------------------------------------------------------------------

URL: localhost:8080/authenticate, METHOD : POST

HEADERS:
Content-Type: application/json

BODY: 

{
	"username": "user",
	"password": "pass"
}

Then you get response like below

{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgwNDY5ODI4LCJpYXQiOjE1ODA0MzM4Mjh9.IVXj2TW9FlvFVlpYxQuUUlnUL7aTDO7fGhNa057sa-k"
}

3. Copy the jwt string from above response and use it as Authorization header in
all subsequent requests

URL: localhost:8080/person

METHOD: GET,PUT,POST,DELETE

HEADERS:

Authorization: Bearer <<jwt response from 2nd step>>

Content-Type: application/json

BODY( for POST,PUT, DELETE):
Example For Post request:

{
    "person": [
        {
            "first_name": "Sarah",
            "last_name": "Robinson",
            "age": "55",
            "favorite_colour": "orange",
            "hobby": [
                "chess"
            ]
        },
        {
            "first_name": "Rick",
            "last_name": "Bell",
            "age": "26",
            "favorite_colour": "green",
            "hobby": [
                "chess", "code"
            ]
        }
    ]
}

NOTE: 
For the POST operation , new person is created only if either of firstname, lastname, age of Person in input is different than the one already stored in DB. 

Also, for the PUT operation , existing person is modified only if firstname, lastname, age of Person in input is matching with the one stored in DB. So, only favorite_colour and hobby is allowed to be modified.

GET will return all person entitites.

4. To delete all users:

URL: localhost:8080/person/all, METHOD: DELETE

HEADERS:

Authorization: Bearer <<jwt response from 2nd step>>

Content-Type: application/json

5. To create other username/password in step 2, you can create your own users using:

URL: localhost:8080/users, 

METHOD: POST ,

HEADERS:
Content-Type: application/json

BODY:

{
	"username": "<<new username>>",
	"password": "<<new password>>"
}
	
Then follow other steps from 2 onwards as mentioned earlier.
