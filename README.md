<img src="readme.jpg" width="165" height="35">

	 
<!-- <a href="#key-features">Video Demo</a> • -->
<a href="http://jentrent.com/punchlist" target="_blank">Live Site</a> 

## Table of Contents
- [Description](#description)
	- [Features](#features)
	- [Technologies](#technologies)
- [Install and Run](#install-and-run)
	- [Dependencies](#dependencies)
	- [Setup](#setup)
- [Author](#author)

## Description
**Punchlist** is a full-stack, Java web application for managing everyday tasks and to-do’s, so you can knock them out quickly and efficiently.  Punchlist is built with the underlying Servlet API as well as JSPs to better learn and apply the underlying Java web platform, as many legacy Java web apps utilize this approach. The application is designed using the Model-View-Controller (MVC) pattern as well as other well-established object-oriented design patterns.  Punchlist also has a [Rest API](src/main/java/com/jentrent/punchlist/api) for all entity CRUD operations.

### Features
Punchlist has the following set of task list features:
- Create a task in a list
- Update a task in a list
- Mark a task as completed
- Unmark a task as completed
- Delete a task
- Create a user account
- Manage a user account

### Technologies
- Back-end: Java/JEE, JPA/Hibernate, Spring, JUnit
- Front End: Servlet, JSP, JSTL, HTML, CSS
- Default app server: Tomcat
- Default DB server: PostgreSQL

## Install and Run
There is <a href="#key-features">Video Demo</a> and <a href="http://jentrent.com/punchlist" target="_blank">Live Site</a> for you to try it out. Or, if you would like to setup and run the project yourself, please use the section below.

### Dependencies

- Java 8 or higher
- Maven
- Java web app server
- PostgreSQL database server

### Setup
1. Create a postgres user account for DB access
2. Log into postgres and run the Punchlist DB create script [`punchlist_create.sql`](src/main/resources/sql/punchlist_create.sql)
3. Add the DB account/pw to the Test [`persistence.xml`](src/test/resources/META-INF/persistence.xml)
4. Add the DB account/pw to the main deployment [`persistence.xml`](src/main/resources/META-INF/persistence.xml)
5. Build the application using the command: `mvn clean install`
6. Deploy the `target/punchlist-1.0.war` to your app server

The build will run the complete set of [JUnit Tests](src/test/java/com/jentrent/punchlist/service/test/) to verify the setup is working as expected. To skip these tests, add the following to the maven build command:  `-Dmaven.test.skip=true`

## Author

Jennifer Trent (<a href="http://jentrent.com" target="_blank">My Website</a>)

[![Follow me on GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/jentrent) 
[![Follow me on LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jenniferltrent/)
[![Email me](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:Jltrent12@gmail.com)

</div>





---
