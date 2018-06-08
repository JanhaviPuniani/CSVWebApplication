# CSVWebApplication
CSVWebApplication is an annotation driven Spring MVC web application developed on Spring Tool Suite Eclipse-based IDE. 
The application uses csv jdbc driver to parse the CSV file and Spring Utils LevenshteinDistance to find the duplicate records.
JSP and JSTL is used to display the duplicate and non-duplicate records.
All the dependencies of project are managed and injected using Maven pom.xml.


## Prerequisites
Install Spring Tool Suite Eclipse-based IDE or Spring Plugin on existing Eclipse on your local machine to run the application.
Install and Configure Apache Tomcat 8.5 server with STS.

## Setting up the Application
Clone the repository or download the zip file from repository
Open the IDE and Import the downloaded project into STS.
To fix the dependencies:
Right click the project -> Maven-> Update Project
Then Clean and Build the Project

## Running the Application
Right click on Application -> Run AS-> Run on Server -> Select Tomcat Server
And application will run at http://localhost:8080/spring/





