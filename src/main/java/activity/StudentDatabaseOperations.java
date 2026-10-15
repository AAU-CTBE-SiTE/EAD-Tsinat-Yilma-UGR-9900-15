package activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Lab 1: connect to MySQL with JDBC and work with the students table.
 *
 * Each run creates StudentsDB (if needed), rebuilds a clean students table,
 * inserts 11 students, then shows, updates, deletes, and averages grades.
 */
public class StudentDatabaseOperations {

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
            insertStudents(connection);
            showFirstFiveStudents(statement);
            updateStudentFirstName(connection);
            deleteStudent(connection);
            printAverageGrade(statement);
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
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS StudentsDB");
        connection.setCatalog("StudentsDB");

        // Drop and recreate so every run starts from the same rows and ids.
        statement.executeUpdate("DROP TABLE IF EXISTS students");
        statement.executeUpdate(
                "CREATE TABLE students ("
                        + "id INT PRIMARY KEY, "
                        + "first_name VARCHAR(50) NOT NULL, "
                        + "last_name VARCHAR(50) NOT NULL, "
                        + "grade DECIMAL(5,2) NOT NULL"
                        + ")");
        System.out.println("Database StudentsDB and table students are ready.");
    }

    private static void insertStudents(Connection connection) throws SQLException {
        String sql = "INSERT INTO students (id, first_name, last_name, grade) VALUES (?, ?, ?, ?)";
        Object[][] students = {
                {1, "Kebede", "Alemu", 85.50},
                {2, "Meron", "Tesfaye", 92.00},
                {3, "Dawit", "Bekele", 78.50},
                {4, "Hana", "Girma", 88.00},
                {5, "Yonas", "Hailu", 74.00},
                {6, "Selam", "Tadesse", 91.50},
                {7, "Biruk", "Mengistu", 81.00},
                {8, "Liya", "Assefa", 86.50},
                {9, "Nahom", "Desta", 79.00},
                {10, "Rahel", "Worku", 94.00},
                {11, "Samuel", "Getachew", 83.50}
        };

        try (PreparedStatement insert = connection.prepareStatement(sql)) {
            for (Object[] student : students) {
                insert.setInt(1, (int) student[0]);
                insert.setString(2, (String) student[1]);
                insert.setString(3, (String) student[2]);
                insert.setDouble(4, (double) student[3]);
                insert.executeUpdate();
            }
        }
        System.out.println("Inserted 11 students.");
    }

    private static void showFirstFiveStudents(Statement statement) throws SQLException {
        String sql = "SELECT id, first_name, last_name, grade FROM students ORDER BY id LIMIT 5";
        System.out.println();
        System.out.println("First 5 students:");
        System.out.printf("%-4s %-12s %-12s %s%n", "ID", "First name", "Last name", "Grade");

        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.printf("%-4d %-12s %-12s %.2f%n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDouble("grade"));
            }
        }
    }

    private static void updateStudentFirstName(Connection connection) throws SQLException {
        String sql = "UPDATE students SET first_name = ? WHERE id = ?";
        try (PreparedStatement update = connection.prepareStatement(sql)) {
            update.setString(1, "Abebe");
            update.setInt(2, 1);
            int rows = update.executeUpdate();
            System.out.println();
            System.out.println("Changed the first name of student 1 to Abebe. Rows updated: " + rows);
        }
    }

    private static void deleteStudent(Connection connection) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, 2);
            int rows = delete.executeUpdate();
            System.out.println("Deleted student 2. Rows deleted: " + rows);
        }
    }

    private static void printAverageGrade(Statement statement) throws SQLException {
        String sql = "SELECT AVG(grade) AS average_grade FROM students";
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                System.out.println();
                System.out.printf("Average grade of all students: %.2f%n", resultSet.getDouble("average_grade"));
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
