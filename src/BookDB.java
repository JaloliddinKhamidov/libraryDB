import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDB {
    Connection connection = connect();
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // loading and registering the Driver using Class.forName
            String url = "jdbc:mysql://localhost:3306/library"; // Update with your database name
            String username = "root";
            String password = "omon2134ADA$";
            /*Establish a connection to the MySQL database using the provided URL, username, and password.*/
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void addBook(Book book) {
        try { // <----We're checking the connection
            // Check if the connection is established
            if (connection != null) {
                // Use a prepared statement to prevent SQL injection
                String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Set parameters for the prepared statement
                    preparedStatement.setString(1, book.getTitle());
                    preparedStatement.setString(2, book.getAuthor());
                    // Execute the update
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Book has been added successfully!");
                    } else {
                        System.out.println("Failed to add the book.");
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getAllBooks(){
        // Retrieve tasks from the database
        try (Statement statement = connection.createStatement()) {
            // Execute a SQL query to select all tasks from the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books"); //ResultSet can store data in a chunk of structure
            while (resultSet.next()) {
                // Retrieve task details from the ResultSet
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQLException that occurs during database interaction
        }
    }
    public void deleteBook(int bookID){
        try(Connection connection = connect()){
            if(connection != null){
                String sql = "DELETE FROM books WHERE id = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    //Set the parameter for the prepared statement
                    preparedStatement.setInt(1, bookID);
                    //Execute update
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.printf("Book with ID: %d has been deleted successfully! \n", bookID);
                    }else{
                        System.out.printf("No book has been found with ID: %d \n", bookID);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateBook(int bookID, String newTitle, String newAuthor, int newID) {
        try(Connection connection = connect()){
        if(connection != null){
            String sql = "UPDATE books SET id = ?, title = ?, author = ? WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, newID);
                preparedStatement.setString(2, newTitle);
                preparedStatement.setString(3, newAuthor);
                preparedStatement.setInt(4, bookID);

                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0){
                    System.out.printf("Book information with ID: %d has been updated successfully! \n", bookID);
                }
                else{
                    System.out.printf("No book has been found with this ID: %d \n", bookID);
                }
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}