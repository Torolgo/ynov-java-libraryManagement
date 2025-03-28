package fr.ynov.librarymanagement.gui.loan;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;

public class LoanWindowManager {

    /**
     * Opens a window for borrowing books.
     * <p>
     * This method creates and displays a form that allows users to borrow books from the library.
     * It first loads all books from storage to ensure the book database is up-to-date, then displays
     * a simple form where the user can enter the ID of the book they wish to borrow. Upon clicking
     * the borrow button, the application attempts to process the book loan and displays appropriate
     * success or error messages.
     * </p>
     */
    static void openTakeBookWindow() {
        loadAllBooks();

        JFrame takeBookFrame = new JFrame("Emprunter un Livre");
        takeBookFrame.setSize(400, 200);
        takeBookFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField idField = new JTextField();
        JButton takeButton = new JButton("Emprunter");

        takeBookFrame.add(new JLabel("ID du Livre:"));
        takeBookFrame.add(idField);
        takeBookFrame.add(takeButton);

        takeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                LoanBookProcessor.processBorrowBook(id, takeBookFrame);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(takeBookFrame, "Erreur: " + ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        takeBookFrame.setVisible(true);
    }

    /**
     * Opens a window for returning books.
     * <p>
     * This method creates and displays a form that allows users to return books to the library.
     * It first loads all books from storage to ensure the book database is up-to-date, then displays
     * a simple form where the user can enter the ID of the book they wish to return. Upon clicking
     * the return button, the application attempts to process the book return and displays appropriate
     * success or error messages.
     * </p>
     */
    static void openReturnBookWindow() {
        loadAllBooks();

        JFrame returnBookFrame = new JFrame("Retourner un Livre");
        returnBookFrame.setSize(400, 200);
        returnBookFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField idField = new JTextField();
        JButton returnButton = new JButton("Retourner");

        returnBookFrame.add(new JLabel("ID du Livre:"));
        returnBookFrame.add(idField);
        returnBookFrame.add(returnButton);

        returnButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                LoanBookProcessor.processReturnBook(id, returnBookFrame);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(returnBookFrame, "Erreur: " + ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        returnBookFrame.setVisible(true);
    }
}