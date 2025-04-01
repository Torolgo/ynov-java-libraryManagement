package fr.ynov.librarymanagement.factory;

import fr.ynov.librarymanagement.domain.Book;
import java.util.ArrayList;
import java.util.List;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;

/**
 * Factory class for managing a list of books and ID generation.
 */
public class BookFactory {
    private static final ArrayList<Book> BOOK_LIST = new ArrayList<>();

    public static List<Book> getBookList() {
        return BOOK_LIST;
    }
    public static void clearBookList() {
        BOOK_LIST.clear();
    }

    /**
     * Returns the next available book ID.
     * <p>
     * This method reloads all books from storage, then finds the highest ID
     * among existing books and returns that value plus one. This ensures
     * that new books receive a unique ID that doesn't conflict with any existing book.
     * </p>
     *
     * @return The next available unique ID for a new book
     */
    public static int getNextAvailableBookId() {
        int maxId = 0;
        loadAllBooks();

        for (Book book : BOOK_LIST) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }

        return maxId + 1;
    }
}