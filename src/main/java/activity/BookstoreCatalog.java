package activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Campus bookstore catalog.
 *
 * Each run creates BookstoreDB, rebuilds the books table, stocks 11 titles,
 * lists the five cheapest books, puts one title on sale, removes a
 * discontinued title, and prints the average shelf price.
 */
public class BookstoreCatalog {

    // Change these if your local MySQL login is different.
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String SERVER_URL =
            "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Connected to MySQL.");

            createDatabaseAndTable(connection, statement);
            stockBooks(connection);
            showFiveCheapest(statement);
            putBookOnSale(connection);
            removeDiscontinuedBook(connection);
            printAveragePrice(statement);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver was not found. Check that mysql-connector-j is on the classpath.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            closeQuietly(statement);
            closeQuietly(connection);
            System.out.println("Connection closed.");
        }
    }

    private static void createDatabaseAndTable(Connection connection, Statement statement) throws SQLException {
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS BookstoreDB");
        connection.setCatalog("BookstoreDB");

        // Drop and recreate so every run starts from the same shelf.
        statement.executeUpdate("DROP TABLE IF EXISTS books");
        statement.executeUpdate(
                "CREATE TABLE books ("
                        + "id INT PRIMARY KEY, "
                        + "title VARCHAR(80) NOT NULL, "
                        + "author VARCHAR(80) NOT NULL, "
                        + "price DECIMAL(6,2) NOT NULL"
                        + ")");
        System.out.println("Database BookstoreDB and table books are ready.");
    }

    private static void stockBooks(Connection connection) throws SQLException {
        String sql = "INSERT INTO books (id, title, author, price) VALUES (?, ?, ?, ?)";
        Object[][] books = {
                {1, "Clean Code", "Robert C. Martin", 45.00},
                {2, "Database Systems", "Ramez Elmasri", 62.50},
                {3, "Effective Java", "Joshua Bloch", 48.00},
                {4, "Design Patterns", "Erich Gamma", 55.00},
                {5, "Pragmatic Programmer", "Andrew Hunt", 42.00},
                {6, "Intro to Algorithms", "Thomas Cormen", 80.00},
                {7, "Computer Networks", "Andrew Tanenbaum", 58.00},
                {8, "OS Concepts", "Abraham Silberschatz", 70.00},
                {9, "Artificial Intelligence", "Stuart Russell", 65.00},
                {10, "Python Crash Course", "Eric Matthes", 35.00},
                {11, "Head First Java", "Kathy Sierra", 40.00}
        };

        try (PreparedStatement insert = connection.prepareStatement(sql)) {
            for (Object[] book : books) {
                insert.setInt(1, (int) book[0]);
                insert.setString(2, (String) book[1]);
                insert.setString(3, (String) book[2]);
                insert.setDouble(4, (double) book[3]);
                insert.executeUpdate();
            }
        }
        System.out.println("Stocked 11 books.");
    }

    private static void showFiveCheapest(Statement statement) throws SQLException {
        String sql = "SELECT id, title, author, price FROM books ORDER BY price, id LIMIT 5";
        System.out.println();
        System.out.println("Five cheapest books:");
        System.out.printf("%-4s %-24s %-24s %s%n", "ID", "Title", "Author", "Price");

        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.printf("%-4d %-24s %-24s %.2f%n",
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"));
            }
        }
    }

    private static void putBookOnSale(Connection connection) throws SQLException {
        String sql = "UPDATE books SET price = ? WHERE id = ?";
        try (PreparedStatement update = connection.prepareStatement(sql)) {
            update.setDouble(1, 40.00);
            update.setInt(2, 1);
            int rows = update.executeUpdate();
            System.out.println();
            System.out.println("Put Clean Code (book 1) on sale at 40.00. Rows updated: " + rows);
        }
    }

    private static void removeDiscontinuedBook(Connection connection) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, 2);
            int rows = delete.executeUpdate();
            System.out.println("Removed discontinued book 2 (Database Systems). Rows deleted: " + rows);
        }
    }

    private static void printAveragePrice(Statement statement) throws SQLException {
        String sql = "SELECT AVG(price) AS average_price FROM books";
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                System.out.println();
                System.out.printf("Average price of books still on the shelf: %.2f%n",
                        resultSet.getDouble("average_price"));
            }
        }
    }

    private static void closeQuietly(AutoCloseable resource) {
        if (resource == null) {
            return;
        }
        try {
            resource.close();
        } catch (Exception e) {
            System.out.println("Could not close a database resource: " + e.getMessage());
        }
    }
}
