import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        BookDB bookDB = new BookDB();
        Scanner scanner = new Scanner(System.in);
        System.out.println("<---- Book Collections ---->");
        System.out.println("1. View available books");
        System.out.println("2. Add a book");
        System.out.println("3. Delete a book");
        System.out.println("4. Update book information");
        System.out.println("5. Exit");
        System.out.println("---------------");
        while(true){
            System.out.print("Choose: ");
            int userChoice = scanner.nextInt();
            switch(userChoice){
                case 1 -> {
                    System.out.println("The library currently includes the following books: ");
                    bookDB.getAllBooks();
                }

                case 2 -> {
                    scanner.nextLine();
                    System.out.print("Specify book title: ");
                    String bookTitle = scanner.nextLine();

                    System.out.print("Specify book author: ");
                    String bookAuthor = scanner.nextLine();

                    Book newBook = new Book(bookTitle, bookAuthor);
                    bookDB.addBook(newBook);
                }
                case 3 -> {
                    System.out.print("Specify a book ID to delete: ");
                    int bookIdToDelete = scanner.nextInt();
                    bookDB.deleteBook(bookIdToDelete);
                }
                case 4 -> {
                    System.out.println("The library currently includes the following books: ");
                    bookDB.getAllBooks();
                    System.out.print("\nSpecify a book ID to update: ");
                    int bookID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Specify the new title: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Specify the new author: ");
                    String newAuthor = scanner.nextLine();

                    System.out.print("Set a new ID: ");
                    int newID = scanner.nextInt();
                    bookDB.updateBook(bookID, newTitle, newAuthor, newID);
                }
                case 5 -> {
                    System.out.println("Bye!");
                    System.exit(userChoice);
                    scanner.close();
                }default -> System.out.println("Choose one of the above options from 1-4");

            }
        }
    }
}