package fr.ynov.librarymanagement.gui.loan;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.Updater;

import javax.swing.JFrame;

import static fr.ynov.librarymanagement.gui.UiUtils.*;

public class LoanBookProcessor {

    /**
     * Traite une demande d'emprunt ou de retour de livre.
     *
     * @param id L'identifiant du livre
     * @param isBorrow true pour un emprunt, false pour un retour
     * @param parentFrame La fenêtre parente
     */
    static void processBookAction(int id, boolean isBorrow, JFrame parentFrame) {
        for (Book book : BookFactory.getBookList()) {
            if (book.getId() == id) {
                boolean currentlyTaken = book.isTaken();

                if (isBorrow && currentlyTaken) {
                    showError(parentFrame, new Exception("Ce livre est déjà emprunté!"));
                    return;
                }

                if (!isBorrow && !currentlyTaken) {
                    showError(parentFrame, new Exception("Ce livre n'est pas emprunté!"));
                    return;
                }

                if (isBorrow) {
                    book.takeBook();
                } else {
                    book.returnBook();
                }

                Updater.updateBookStatus(book);
                showSuccessAndClose(parentFrame,
                        isBorrow ? "Livre emprunté avec succès!" : "Livre retourné avec succès!");
                return;
            }
        }
        showError(parentFrame, new Exception("Livre non trouvé!"));
    }
}