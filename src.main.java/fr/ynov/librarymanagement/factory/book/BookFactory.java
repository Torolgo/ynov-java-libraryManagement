package fr.ynov.librarymanagement.factory.book;

import fr.ynov.librarymanagement.domain.Book;
import java.util.ArrayList;
import java.util.List;

public class BookFactory {
    private static final ArrayList<Book> BOOK_LIST = new ArrayList<>();

    public static List<Book> getBookList() {
        return BOOK_LIST;
    }

    public static void clearBookList() {
        BOOK_LIST.clear();
    }

    /**
     * Loads all books from the files into the book list.
     * This method clears the current book list before loading new data.
     */
    public static void loadAllBooks() {
        clearBookList();
        BookReader.readNovelsFile();
        BookReader.readBdsFile();
        BookReader.readMangasFile();
    }

    /**
     * Adds a book to the list of books.
     *
     * @param book The book to add
     */
    public static void updateBookStatus(Book book) {
        BookUpdater.updateBookStatus(book);
    }

    /**
     * Returns the next available book ID by checking the current maximum ID in the list.
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