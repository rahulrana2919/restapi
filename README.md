# restapi
Rest API for Person entities using Springboot and Spring Security

Run the project
1. App
2. Using REST client e.g postman, hit a POST request at below URL
URL: localhost:8080/authenticate, METHOD : POST
Headers:
Content-Type: application/json
Body: 
{
	"username": "user",
	"password": "pass"
}

The you get response like below
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTgwNDY5ODI4LCJpYXQiOjE1ODA0MzM4Mjh9.IVXj2TW9FlvFVlpYxQuUUlnUL7aTDO7fGhNa057sa-k"
}

3. Copy the jwt string from above response and use it as Authorization header in
all subsequent requests
URL: localhost:8080/person, METHOD: GET,PUT,POST,DELETE
Headers:
Authorization: Bearer <<jwt from above step>>
Content-Type: application/json
Body:
For Post request:
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

4. To delete all use:
URL: localhost:8080/person/all, METHOD: DELETE
Headers:
Authorization: Bearer <<jwt from above step>>
Content-Type: application/json