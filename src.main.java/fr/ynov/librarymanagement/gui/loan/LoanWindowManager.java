package fr.ynov.librarymanagement.gui.loan;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.factory.book.BookFactory.loadAllBooks;

public class LoanWindowManager {

    /**
     * Opens the window for borrowing a book.
     * <p>
     * This method creates and displays a window with a text field for entering the book ID
     * and a button to borrow the book. It also loads all books from the factory.
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
     * Opens the window for returning a book.
     * <p>
     * This method creates and displays a window with a text field for entering the book ID
     * and a button to return the book. It also loads all books from the factory.
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