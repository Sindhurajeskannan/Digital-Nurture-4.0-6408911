import java.util.Arrays;
import java.util.Comparator;

class Book {
    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
}

public class LibraryManagementSystem {

    // Linear Search by Title
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search by Title (Assumes sorted array)
    public static Book binarySearchByTitle(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = books[mid].title.compareToIgnoreCase(title);

            if (compare == 0) {
                return books[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    // Display book details
    public static void displayBook(Book book) {
        if (book != null) {
            System.out.println("Book Found - ID: " + book.bookId + ", Title: " + book.title + ", Author: " + book.author);
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "The Alchemist", "Paulo Coelho"),
            new Book(2, "To Kill a Mockingbird", "Harper Lee"),
            new Book(3, "1984", "George Orwell"),
            new Book(4, "Pride and Prejudice", "Jane Austen"),
            new Book(5, "Moby Dick", "Herman Melville")
        };

        // Linear Search
        System.out.println(" Linear Search for '1984':");
        Book linearResult = linearSearchByTitle(books, "1984");
        displayBook(linearResult);

        // Sort the array before Binary Search
        Arrays.sort(books, Comparator.comparing(book -> book.title.toLowerCase()));

        // Binary Search
        System.out.println("\n Binary Search for 'Pride and Prejudice':");
        Book binaryResult = binarySearchByTitle(books, "Pride and Prejudice");
        displayBook(binaryResult);
    }
}
