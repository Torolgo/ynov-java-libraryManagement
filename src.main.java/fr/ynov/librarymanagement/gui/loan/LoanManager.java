package fr.ynov.librarymanagement.gui.loan;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

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

        JButton btnTakeBook = new JButton("Emprunter un Livre");
        JButton btnReturnBook = new JButton("Retourner un Livre");

        btnTakeBook.addActionListener(e -> LoanWindowManager.openTakeBookWindow());
        btnReturnBook.addActionListener(e -> LoanWindowManager.openReturnBookWindow());

        loanFrame.add(btnTakeBook);
        loanFrame.add(btnReturnBook);
        loanFrame.setVisible(true);
    }
}