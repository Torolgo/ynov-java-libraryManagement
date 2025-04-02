package fr.ynov.librarymanagement.gui.loan;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.Updater;
import fr.ynov.librarymanagement.gui.utils.Display;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.GridLayout;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;
import static fr.ynov.librarymanagement.gui.utils.Adder.addButtonToFrame;
import static fr.ynov.librarymanagement.gui.utils.Creator.createLabeledTextField;

/**
 * Manages book loan operations by providing interfaces to borrow and return books while ensuring proper status updates and user feedback.
 */
public class LoanManager {

    /**
     * Opens the main loan management window.
     * <p>
     * This method creates and displays a window with options for managing book loans in the library.
     * It includes buttons for borrowing books and returning books that have been previously borrowed.
     * The window uses a vertical grid layout with two main action buttons that trigger their
     * respective loan operation windows.
     * </p>
     */
    public static void openLoanWindow() {
        JFrame loanFrame = new JFrame("Gérer les Emprunts");
        loanFrame.setSize(400, 300);
        loanFrame.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(loanFrame, "Emprunter un Livre", e -> openBookLoanWindow(true));
        addButtonToFrame(loanFrame, "Retourner un Livre", e -> openBookLoanWindow(false));

        loanFrame.setVisible(true);
    }

    /**
     * Opens a window for borrowing or returning a book based on its ID.
     * <p>
     * This method creates and displays a form that allows users to enter a book ID
     * to either borrow or return a book, depending on the value of the isTakingBook parameter.
     * The window displays different titles and button labels based on the operation type.
     * When the form is submitted, the entered book ID is validated and processed accordingly.
     * </p>
     * <p>
     * Before displaying the window, this method loads all books from storage to ensure
     * that the most up-to-date book information is available for processing the loan operation.
     * </p>
     *
     * @param isTakingBook True if the operation is to borrow a book, false if it's to return a book
     */
    private static void openBookLoanWindow(boolean isTakingBook) {
        loadAllBooks();

        String title = isTakingBook ? "Emprunter un Livre" : "Retourner un Livre";
        JFrame bookLoanFrame = new JFrame(title);
        bookLoanFrame.setSize(400, 200);
        bookLoanFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField idField = createLabeledTextField(bookLoanFrame, "ID du Livre");

        String buttonText = isTakingBook ? "Emprunter" : "Retourner";
        addButtonToFrame(bookLoanFrame, buttonText, e -> {
            // Validate the input and process the loan operation
            try {
                int id = Integer.parseInt(idField.getText());
                processBookAction(id, isTakingBook, bookLoanFrame);
            } catch (Exception ex) {
                Display.showError(bookLoanFrame, ex);
            }
        });

        bookLoanFrame.setVisible(true);
    }

    /**
     * Processes a book loan or return action based on the book ID.
     * <p>
     * This method searches for a book with the specified ID and performs either a borrow
     * or return operation depending on the isBorrow parameter. It performs validation
     * checks to ensure that:
     * - Books that are already borrowed cannot be borrowed again
     * - Books that are not currently borrowed cannot be returned
     * </p>
     * <p>
     * If the operation is valid, it updates the book's status and saves the changes to storage.
     * The method displays appropriate success or error messages to the user based on the
     * outcome of the operation.
     * </p>
     *
     * @param id          The ID of the book to process
     * @param isBorrow    True if this is a borrow operation, false if it's a return operation
     * @param parentFrame The parent JFrame used for displaying messages to the user
     */
    private static void processBookAction(int id, boolean isBorrow, JFrame parentFrame) {
        for (Book book : BookFactory.getBookList()) {
            if (book.getId() == id) {
                boolean currentlyTaken = book.isTaken();

                if (isBorrow && currentlyTaken) {
                    Display.showError(parentFrame, new Exception("Ce livre est déjà emprunté!"));
                    return;
                }

                if (!isBorrow && !currentlyTaken) {
                    Display.showError(parentFrame, new Exception("Ce livre n'est pas emprunté!"));
                    return;
                }

                if (isBorrow) {
                    book.takeBook();
                } else {
                    book.returnBook();
                }

                Updater.updateBookStatus(book);
                Display.showSuccessAndClose(parentFrame,
                        isBorrow ? "Livre emprunté avec succès!" : "Livre retourné avec succès!");
                return;
            }
        }
        Display.showError(parentFrame, new Exception("Livre non trouvé!"));
    }
}