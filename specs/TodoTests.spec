# Todo Specification
Tags: todo, web

## Register User in order to authenticate, create and update task using api
Tags: 1234

* Register user for login using api service
   |key      |value                 |
   |---------|----------------------|
   |email    | ains@gemini.com      |
   |password | T3sting!23           |
* Authenticate user with username "ains@gemini.com" and password "T3sting!23"
* Create task using service
    |key        | value                                                |
    |-----------|------------------------------------------------------|
    |title      | username_today                                       |
    |description| This task is to automate a gemini sample application |
    |is complete| No                                                   |
* Update task with details using service
    |key         |value                      |
    |------------|---------------------------|
    |is complete | yes                       |
    |description | this is a updated message |

## Get all users using api
Tags: 123456

* Register user for login using api service
   |key      |value                 |
   |---------|----------------------|
   |email    | user3@gemini.com     |
   |password | T3sting!23           |
* Register user for login using api service
   |key      |value                 |
   |---------|----------------------|
   |email    | user4@gemini.com     |
   |password | T3sting!23           |
* Authenticate user with username "user3@gemini.com" and password "T3sting!23"
* Get all users using service
* Validate current user is unauthorized for get all users request

## Register new user
Tags: new_user

Given I am on register page
When I complete registration
Then I should successful user creation message appear

* User goes to Todo Login Page
* Validate Todo Login page displays as required
* Click Register button on Login Page
* Validate Register page is displayed
* Enter user registration details
    |key      |value                  |
    |---------|-----------------------|
    |email    | zainglenn@outlook.com |
    |password | T3sting!23            |
* Click Register button
* Validate successful registration message is displayed - "User zainglenn@outlook.com successfully registered. Proceed to login."

## Register User in order to login and create task
Tags: create_tasks

* Register user for login using api service skip if exist
   |key      |value                 |
   |---------|----------------------|
   |email    | zain.glenn@gemini.com|
   |password | T3sting!23           |
* User goes to Todo Login Page
* Validate Todo Login page displays as required
* Enter username "zain.glenn@gemini.com" and password "T3sting!23"
* Click Login Button
* Validate user is on home/tasks page
* Click add task button
* Validate add task page is displayed
* Enter add task details
    |key         | value                                                |
    |------------|------------------------------------------------------|
    |title       | Complete Gemini Assessment                           |
    |description | This task is to automate a gemini sample application |
    |is complete | Yes                                                  |
* Click save button on add task page
* Validate task is created and displayed as required
     |key         | value                                                |
     |------------|------------------------------------------------------|
     |row         | 1                                                    |
     |title       | Complete Gemini Assessment                           |
     |description | This task is to automate a gemini sample application |
     |is complete | Yes                                                  |
* Delete task at row "1"
* Validate task is deleted
     |key         | value                                                |
     |------------|------------------------------------------------------|
     |row         | 1                                                    |
     |title       | Complete Gemini Assessment                           |
     |description | This task is to automate a gemini sample application |
     |is complete | Yes                                                  |
* Logout of todo application
___
* Shut down browser