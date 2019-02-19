# Task Tracker (Back-end) ...work in progress
A project designed to manage tasks of employees working on a certain project. Only authenticated users may use the app. There are two roles, Admin and User. Admin is authorized to add new projects and create tasks for users thet are working on that project.
  
## Getting Started

These instructions will get you a copy of the project up and running on your loacl machine for development and testing purposes.

### Installing

A step by step guide that tells you how to get a development env running.

First step:
```
You need to create a database. Inside TaskTracker-back/sql you will find createDataBase.sql file. 
That is a script for creating a database.
```
Second step
```
You need to change application.properties file that is located inside src/main/resources folder. 
Change spring.jpa.hibernate.ddl-auto=update to spring.jpa.hibernate.ddl-auto=create (This is needed 
to 
create tables in your
database).
```
Now you run the app
```
This is done by right clicking on the App.java file (src/main/java/com/tasktracker/App.java) and 
selecting Run As - Java Application. This will start the application and create tables inside your 
database.
```
Fourth step
```
Open application.properties file again and return the value for the spring.jpa.hibernate.ddl-auto=update.
This is done because you dont want to create new tables every time you run the app (You will lose data
stored inside your database).
```
Final step
```
Add initial data to your database. Open sql folder once again and run the initialData.sql script. Run 
the app and you are good to go!
```
## Built With

* [Maven](https://maven.apache.org/what-is-maven.html) - Dependency Management
