# EA-Task4
Java Spring application. Reading and manipulating data from database. Thymeleaf controller and REST API controller implemented.
See a live version here: iclone-experis.herokuapp.com

## About
This application is using Spring + Thymeleaf to display data on HTML templates from Chinook Sqlite database. The application also exposes an RESTFUL API to perfrom CURD operations.
The Thymeleaf root page displays 5 random genres, artists and songs selected from the database. Search functionality for songs is also implemented.

### Usage

API supports following functionality:

GET: api/rest/customers - returns all customers in the database

GET: api/rest/customers/{id} - returns customers info

GET: api/rest/country/total/customers - returns all countries and their customer counts 

GET: api/rest/customers/invoice/total - returns all customers and how much money they have spent

GET: api/rest/customers/{id}/popular/genre - returns favorite genre (or two in case of a tie) of a given customer

POST: api/rest/customers - creates a new customer, see below supported JSON syntax (PrivateKey UserId generates automatically)
```
{
    "firstName" : "New",
    "lastName" : "Customer",
    "postalCode": "00000", 
    "country" : "Moon",
    "email" :"new@customer.com",
    "phoneNumber" : "123456"
}
```
PUT: api/rest/customers/{id} - updates the customer's info, see below supported JSON syntax

```
{   
    "firstName" : "newName",
    "lastName" : "newLastName",
    "customerId" : "59",
    "postalCode": "11111", 
    "country" : "Mars",
    "email" :"newemail@email.com",
    "phoneNumber" : "654321"
}
```

Live version (and the API) can be tried in: iclone-experis.herokuapp.com
