package fr.ynov.librarymanagement.gui.loan;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

public class LoanWindowManager {
    /**
     * Opens a window for borrowing a book.
     */
    static void openTakeBookWindow() {
        LoanBookProcessor.loadBooks();

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
     * Opens a window for returning a previously borrowed book.
     */
    static void openReturnBookWindow() {
        LoanBookProcessor.loadBooks();

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