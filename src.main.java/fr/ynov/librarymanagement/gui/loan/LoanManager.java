package fr.ynov.librarymanagement.gui.loan;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

public class LoanManager {
    /**
     * Opens the main loan management window with options to borrow or return books.
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