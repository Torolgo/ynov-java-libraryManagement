package fr.ynov.librarymanagement.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

import static fr.ynov.librarymanagement.gui.BookManager.openBookWindow;
import static fr.ynov.librarymanagement.gui.LoanManager.openLoanWindow;
import static fr.ynov.librarymanagement.gui.PersonManager.openAuthorWindow;

/**
 * Manages the main graphical user interface of the library management system.
 */
public class GuiManager {

    /**
     * Creates and configures a panel containing the main navigation buttons.
     *
     * @return A JPanel containing buttons for Author, Book, and Loan management
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
     * Creates and displays the main application window with navigation options.
     * Sets up the main frame with a welcome message and navigation buttons
     * to access different modules of the library management system.
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
}
