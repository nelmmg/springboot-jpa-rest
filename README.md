# Transactions API

## Contents

1. Context and Purpose
2. Business Requirements
3. Rest Contract
4. 3rd Party Contract
5. Required Configurations

___
### 1. Context and Purpose

The purpose of this project evaluate the ability to build a microservice which interacts with a RestAPI and a database.  

The project has a TransactionsJourney class under the test folder, which proves the solution is working according the requirements below.  

Don't change anything on that class in order to make the test pass. Once the microservice is implemented you will get the green passed on that.  

You are free to use any libraries to reach the requirements and you can decide which project/package structure works the best for you.  

There is no time limit to complete this task, but please use GIT during the development when you find it appropriate. 

As a staring point, you will have available the RestController for the API, please don't change the already defined endpoints.
___
### 2. Business Requirements

####Retrieve all the transactions
An endpoint to retrieve all the transactions executed, without any filters;

#### Retrieve the transactions executed by
An endpoint to retrieve the transactions executed by a specific sender;

#### Execute a transaction
An endpoint execute a transaction from A to B of the defined amount.
A transaction is considered done when the following operations are done:
- Calling the 3rd Party to execute and receive the transaction ID;
- Store the transaction ID and details into the database.


___
### 3. Rest Contract
Our RestAPI will provide 3 endpoints:  
  
    GET /api/transactions/{senderId} - Returns the transactions performed by a sender (Model Transaction) 
    GET /api/transactions - Returns all the transactions (Model Transaction)
    POST /api/transaction - Executes a new transaction
    
    (Model names are just indicative, you call named them as you wish)

##### GET  /api/transactions/{senderId}

Input:

    PATH: senderId - String

Output:
   
    [   
       { 
         "id":"eb490cfa-5a34-45b7-9bc2-1e8d1ee74395", 
         "sender":"senderId", 
         "receiver":"receivedId",
         "amount": 10.0
       }, 
       {...}
    ] 

##### GET  /api/transactions

Input:

    Not Applicable

Output:
   
    [   
       { 
         "id":"eb490cfa-5a34-45b7-9bc2-1e8d1ee74395", 
         "sender":"senderId", 
         "receiver":"receivedId",
         "amount": 10.0
       }, 
       {...}
    ] 

##### POST  /api/transaction

Input:

    { 
       "sender":"senderId", 
       "receiver":"receivedId",
       "amount": 10.0 (example)
    } 

Output:
   
    {
      "id":"eb490cfa-5a34-45b7-9bc2-1e8d1ee74395" 
    }

___
### 4. 3rd Party Contract

The 3rd will be responsible for executing the transaction and return a OK or NOT OK.

There is one endpoint available:

    POST /executor/transaction
    
##### POST  /executor/transaction
Input:

    { 
       "sender":"senderId", 
       "receiver":"receivedId",
       "amount": 10.0 (example)
    } 

Output:
   
    {
      "id":"eb490cfa-5a34-45b7-9bc2-1e8d1ee74395" 
    }



___
### 5. Required Configuration

##### 3rd Party Config
This project is prepared to test the E2E solution. To do this, it is mocking the 3rd party endpoint.  
Your service responsible to call the 3rd party will need to read this URL from the configuration.  
 
Please DON'T change this variable name, feel free to change the values for local testing - application.yml:
    
    executor:
      endpoint: http://localhost:9090/executor/transaction

