Spring JPA application performs the CRUD(Create,Read,Update,Delete operation in the database.

Technology stack
---------------- 

Java EE JDK1.8, JSF2.2 and Oracle DB(OJDBC6). 

Server
-------
Tomcat 9.0 / jboss-eap-6.4


Newly Added packages/Files
-------------------------
src.main.java.com.crud - Server SourceCode
src.main.resources - messages.properties
Configuration files - web.xml,spring-servelet.xml,pom.xml for dependencies
src.main.webapp.WEB-INF.views.listEmployeeView.jsp,src.main.webapp.index.jsp - Client SourceCode

Oracle Database
---------------- 

SQL Scripts
-----------

DDL
---
CREATE TABLE DEPARTMENT
(
    EMP_DEPT_ID NUMBER(*, 20) NOT NULL,
    DEPARTMENTNAME VARCHAR2(40) NOT NULL,
    CONSTRAINT DEPARTMAN_PK PRIMARY KEY (EMP_DEPT_ID)
);
CREATE TABLE EMPLOYEE (
   EMP_ID NUMBER(*, 20) NOT NULL,
    EMP_NAME VARCHAR2(40) NOT NULL,
    EMP_ADDRESS VARCHAR2(40) ,
	EMP_DEPT_ID NUMBER(*, 20) NOT NULL,
    PRIMARY KEY (EMP_ID),
    CONSTRAINT FK_DepartmentID FOREIGN KEY (EMP_DEPT_ID)
    REFERENCES DEPARTMENT(EMP_DEPT_ID)
);

Jars added
----------

Please refer WebContent/lib folder in the sourcecode directory.

IDE used
--------

Eclipse Oxygen


Steps to Execute
----------------
(1).Create a dyamic web project in eclipse IDE.
(2).In the pom.xml, add the dependencies of maven if required and setup the configuration files web.xml,spring-servelet.xml for the project.
(3).Add all the libraries required for springs and jsf
(4).Once after implementation clean build the project.
(5).Compile either using eclipse or using a bat file.
(6).Create a database connection in Oracle 11g
(7).Create tables required to perform the CRUD operations(Employee and Department)
(8).Start the server & execute the localhost with the port 8787.
(9).Optimise the code before submission.

P.S: NOTE that for this CRUD example not able to use much of Java8 functional interfaces as already the code written is optimised.