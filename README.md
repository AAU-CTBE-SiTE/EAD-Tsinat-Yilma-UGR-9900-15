# Lab 1: Java Maven Starter Project

A Java Maven project that connects to MySQL with JDBC and works with a `students` table.

## What the program does

1. Creates a database called `StudentsDB` and a `students` table
2. Adds 11 students to the table
3. Shows the first 5 students
4. Changes the first name of student 1 to `Abebe`
5. Deletes student 2
6. Prints the average grade of the remaining students

You can run it as many times as you want. It rebuilds the table at the start, so you always get the same result. After student 2 is deleted, the average grade is **84.15**.

## How it works

The program opens a connection to MySQL, then creates a `Statement` that sends SQL to the database. Each task is a different SQL command. Insert, update, and delete use a `PreparedStatement` because those statements need values. At the end it closes the statement and the connection.

## Project structure

```text
pom.xml
src/main/java/activity/StudentDatabaseOperations.java
```

## What you need

- JDK 17 or newer
- MySQL running on `localhost:3306`
- Maven

The program uses username `root` and password `root`. If your MySQL login is different, change `USER` and `PASSWORD` at the top of `main` in `StudentDatabaseOperations.java`.

## How to run

From this folder:

```powershell
mvn compile
mvn exec:java -Dexec.mainClass=activity.StudentDatabaseOperations
```

## Without Maven

You can also use Eclipse. Download the MySQL Connector/J `.jar`, add it to the build path, and run `StudentDatabaseOperations`.
