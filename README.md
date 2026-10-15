# Lab 1: Campus Bookstore Catalog

A Java Maven project that connects to MySQL with JDBC and manages the shelf of a campus bookstore.

## What the program does

1. Creates a database called `BookstoreDB` and a `books` table
2. Stocks 11 books (title, author, and price)
3. Shows the five cheapest books
4. Puts book 1, *Clean Code*, on sale at 40.00
5. Removes discontinued book 2, *Database Systems*
6. Prints the average price of the books still on the shelf

You can run it as many times as you want. It rebuilds the table at the start, so you always get the same result. After the sale and the removal, the average price is **53.30**.

## How it works

The program opens a connection to MySQL, then creates a `Statement` that sends SQL to the database. Each task is a different SQL command. Insert, update, and delete use a `PreparedStatement` because those statements need values. At the end it closes the statement and the connection.

## Project structure

```text
pom.xml
src/main/java/activity/BookstoreCatalog.java
```

## What you need

- JDK 17 or newer
- MySQL running on `localhost:3306`
- Maven

The program uses username `root` and password `root`. If your MySQL login is different, change `USER` and `PASSWORD` at the top of `main` in `BookstoreCatalog.java`.

## How to run

From this folder:

```powershell
mvn compile
mvn exec:java -Dexec.mainClass=activity.BookstoreCatalog
```

## Without Maven

You can also use Eclipse. Download the MySQL Connector/J `.jar`, add it to the build path, and run `BookstoreCatalog`.
