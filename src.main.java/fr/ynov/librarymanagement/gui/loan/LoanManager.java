package fr.ynov.librarymanagement.gui.loan;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.UiUtils.addButtonToFrame;

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

        addButtonToFrame(loanFrame, "Emprunter un Livre", e -> LoanWindowManager.openTakeBookWindow());
        addButtonToFrame(loanFrame, "Retourner un Livre", e -> LoanWindowManager.openReturnBookWindow());

        loanFrame.setVisible(true);
    }
}