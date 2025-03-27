package fr.ynov.librarymanagement.gui;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

/**
 * Manages book loan operations through a graphical user interface.
 * Provides functionality for borrowing and returning books from the library.
 */
public class LoanManager {

    /**
     * Opens the main loan management window with options to borrow or return books.
     * Creates a window with two buttons for the main loan operations.
     */
    public static void openLoanWindow() {
        JFrame loanFrame = new JFrame("Gérer les Emprunts");
        loanFrame.setSize(400, 300);
        loanFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnTakeBook = new JButton("Emprunter un Livre");
        JButton btnReturnBook = new JButton("Retourner un Livre");

        btnTakeBook.addActionListener(e -> openTakeBookWindow());
        btnReturnBook.addActionListener(e -> openReturnBookWindow());

        loanFrame.add(btnTakeBook);
        loanFrame.add(btnReturnBook);
        loanFrame.setVisible(true);
    }

    /**
     * Opens a window for borrowing a book.
     * Allows the user to enter a book ID and borrow it if available.
     * Updates the book's status in the storage system after a successful loan.
     */
    private static void openTakeBookWindow() {
        BookFactory.clearBookList();
        BookFactory.readNovelsFile();
        BookFactory.readBdsFile();
        BookFactory.readMangasFile();

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
                for (Book book : BookFactory.getBookList()) {
                    if (book.getId() == id) {
                        if (book.isTaken()) {
                            JOptionPane.showMessageDialog(takeBookFrame, "Ce livre est déjà emprunté!",
                                    "Information", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        book.takeBook();
                        BookFactory.updateBookStatus(book);
                        JOptionPane.showMessageDialog(takeBookFrame, "Livre emprunté avec succès!");
                        takeBookFrame.dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(takeBookFrame, "Livre non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(takeBookFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        takeBookFrame.setVisible(true);
    }

    /**
     * Opens a window for returning a previously borrowed book.
     * Allows the user to enter a book ID and return it to the library.
     * Updates the book's status in the storage system after a successful return.
     */
    private static void openReturnBookWindow() {
        BookFactory.clearBookList();
        BookFactory.readNovelsFile();
        BookFactory.readBdsFile();
        BookFactory.readMangasFile();

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
                for (Book book : BookFactory.getBookList()) {
                    if (book.getId() == id) {
                        if (!book.isTaken()) {
                            JOptionPane.showMessageDialog(returnBookFrame, "Ce livre n'est pas emprunté!",
                                    "Information", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        book.returnBook();
                        BookFactory.updateBookStatus(book);
                        JOptionPane.showMessageDialog(returnBookFrame, "Livre retourné avec succès!");
                        returnBookFrame.dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(returnBookFrame, "Livre non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(returnBookFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        returnBookFrame.setVisible(true);
    }
}