package fr.ynov.librarymanagement.gui.loan;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.Updater;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoanBookProcessor {

    /**
     * Processes a book borrowing request using the book's ID.
     * <p>
     * This method searches for a book with the specified ID in the book collection.
     * If found, it checks if the book is available for borrowing. If available,
     * the book's status is updated to borrowed, and a success message is displayed.
     * If the book is already borrowed or not found, an appropriate message is shown.
     * </p>
     *
     * @param id          The unique identifier of the book to borrow
     * @param parentFrame The parent frame from which this action was triggered
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
                Updater.updateBookStatus(book);
                JOptionPane.showMessageDialog(parentFrame, "Livre emprunté avec succès!");
                parentFrame.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(parentFrame, "Livre non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Processes the return of a book by its ID.
     * <p>
     * This method searches for a book with the specified ID in the book collection.
     * If found, it checks if the book is currently borrowed. If borrowed,
     * the book's status is updated to available, and a success message is displayed.
     * If the book is not borrowed or not found, an appropriate message is shown.
     * </p>
     *
     * @param id          The unique identifier of the book to return
     * @param parentFrame The parent frame from which this action was triggered
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
                Updater.updateBookStatus(book);
                JOptionPane.showMessageDialog(parentFrame, "Livre retourné avec succès!");
                parentFrame.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(parentFrame, "Livre non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}