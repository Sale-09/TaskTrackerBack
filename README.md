# Task Tracker (Back-end)

This is an application for managing tasks of a certain project. It is build using java technologies Spring Boot, Spring Security 
and JPA. There are two user roles, Admin and User. Admin is allowed to create a new Project and add Tasks to that Project 
for a specific user to complete.
  
Project contains:
  * project id
  * project name
  * list of tasks

Task Contains:
  * task id
  * date that represents a deadline for a task to be completed
  * boolean value that shows if a certain task is completed
  * list of comments
  * user who needs to complete the given task
  * project that contains that task
  
 Comment contains:
  * comment id
  * text
  * date when the comment was created
  * task that contains a comment
  * user who created the comment

## Running the Application

First you need to create a DataBase. There is a file createDataBase.sql that you need to open with MySql Workbench and run.
File is located inside the sql folder. After the Database is created, open project in your IDE and open application,properties file
that is located here, src/main/resources. Once you oppend the file you need to change a property spring.jpa.hibernate.ddl-auto=update
to spring.jpa.hibernate.ddl-auto=CREATE and save the file in order to create tables for your database. Open application.properties, 
return the value to spring.jpa.hibernate.ddl-auto=update and you are good to go! 
If you want to populate your database with some initial data, there is a file initialData.sql in sql folder that you can run in MySql
Workbench.


