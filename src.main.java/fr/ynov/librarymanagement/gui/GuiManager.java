package fr.ynov.librarymanagement.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import static fr.ynov.librarymanagement.gui.book.BookManager.openBookWindow;
import static fr.ynov.librarymanagement.gui.loan.LoanManager.openLoanWindow;
import static fr.ynov.librarymanagement.gui.person.PersonManager.openAuthorWindow;

public class GuiManager {

    /**
     * Creates and configures the main navigation panel for the library application.
     * <p>
     * This method builds a panel containing three primary navigation buttons:
     * - AUTEUR: Opens the Authors and Illustrators management window
     * - LIVRE: Opens the Books management window
     * - EMPRUNT: Opens the Loans management window
     * </p>
     * <p>
     * The panel uses a vertical grid layout with each button styled with the same
     * font characteristics. Each button has an action listener that directs to
     * the appropriate management screen when clicked.
     * </p>
     *
     * @return A configured JPanel with navigation buttons
     */
    private static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        JButton btnAuthor = new JButton("AUTEUR");
        JButton btnBook = new JButton("LIVRE");
        JButton btnLoan = new JButton("EMPRUNT");

        Font font = new Font("Arial", Font.BOLD, 14);
        btnAuthor.setFont(font);
        btnBook.setFont(font);
        btnLoan.setFont(font);

        btnBook.addActionListener(e -> openBookWindow());
        btnAuthor.addActionListener(e -> openAuthorWindow());
        btnLoan.addActionListener(e -> openLoanWindow());

        panel.add(btnAuthor);
        panel.add(btnBook);
        panel.add(btnLoan);
        return panel;
    }

    /**
     * Creates and displays the main application window for the library management system.
     * <p>
     * This is the entry point for the GUI application. It sets up the primary window with:
     * - A title "BIBLIO-Torolgo"
     * - A welcome message at the top
     * - A navigation panel in the center with buttons for accessing different modules
     *   (Authors/Illustrators, Books, and Loans)
     * </p>
     * <p>
     * The window uses BorderLayout to organize its components, with the navigation panel
     * taking up the center portion and the welcome message at the top.
     * </p>
     */
    public static void guiInterfaceManager() {
        JFrame frame = new JFrame("BIBLIO-Torolgo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenue dans la bibliothèque en ligne", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panel = getJPanel();

        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    /**
     * Displays an error message in a dialog.
     * <p>
     * This method shows a dialog with the specified error message.
     * </p>
     *
     * @param frame The parent JFrame for the dialog
     */
    public static void showError(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Erreur: Une erreur est survenue lors de l'enregistrement ",
                "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
