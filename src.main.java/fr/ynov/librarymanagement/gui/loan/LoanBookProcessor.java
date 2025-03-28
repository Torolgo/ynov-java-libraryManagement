package fr.ynov.librarymanagement.gui.loan;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoanBookProcessor {
    /**
     * Loads all books from storage.
     */
    static void loadBooks() {
        BookFactory.clearBookList();
        BookFactory.readNovelsFile();
        BookFactory.readBdsFile();
        BookFactory.readMangasFile();
    }

    /**
     * Processes the borrowing of a book by its ID.
     *
     * @param id The ID of the book to borrow
     * @param parentFrame The frame from which the request was made
     */
    static void processBorrowBook(int id, JFrame parentFrame) {
        for (Book book : BookFactory.getBookList()) {
            if (book.getId() == id) {
                if (book.isTaken()) {
                    JOptionPane.showMessageDialog(parentFrame, "Ce livre est déjà emprunté!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                book.takeBook();
                BookFactory.updateBookStatus(book);
                JOptionPane.showMessageDialog(parentFrame, "Livre emprunté avec succès!");
                parentFrame.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(parentFrame, "Livre non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Processes the return of a book by its ID.
     *
     * @param id The ID of the book to return
     * @param parentFrame The frame from which the request was made
     */
    static void processReturnBook(int id, JFrame parentFrame) {
        for (Book book : BookFactory.getBookList()) {
            if (book.getId() == id) {
                if (!book.isTaken()) {
                    JOptionPane.showMessageDialog(parentFrame, "Ce livre n'est pas emprunté!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                book.returnBook();
                BookFactory.updateBookStatus(book);
                JOptionPane.showMessageDialog(parentFrame, "Livre retourné avec succès!");
                parentFrame.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(parentFrame, "Livre non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}