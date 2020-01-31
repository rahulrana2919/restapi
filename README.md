# restapi
Rest API for Person entities using Springboot and Spring Security(Jwt)

Run the project
1. Project need to be imported in intellij and Run using the com.embl.restapi.App file in intellij.
2. Using REST client e.g postman, hit a POST request at below URL
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
URL: localhost:8080/person, METHOD: GET,PUT,POST,DELETE
HEADERS:
Authorization: Bearer <<jwt response from 2nd step>>
Content-Type: application/json
BODY:
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
            "first_name": "Pooja",
            "last_name": "Mehne",
            "age": "26",
            "favorite_colour": "green",
            "hobby": [
                "chess", "code"
            ]
        }
    ]
}

4. To delete all users:
URL: localhost:8080/person/all, METHOD: DELETE
HEADERS:
Authorization: Bearer <<jwt response from 2nd step>>
Content-Type: application/json

5. To create other username/password in step 2, you can create your own users using:
URL: localhost:8080/users, METHOD: POST ,
HEADERS:
Content-Type: application/json
BODY:
{
	"username": "<<new username>>",
	"password": "<<new password>>"
}
	
Then follow other steps from 2 onwards as mentioned earlier.
