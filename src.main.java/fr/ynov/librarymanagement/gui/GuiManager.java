package fr.ynov.librarymanagement.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.book.BookManager.openBookWindow;
import static fr.ynov.librarymanagement.gui.loan.LoanManager.openLoanWindow;
import static fr.ynov.librarymanagement.gui.person.PersonManager.openPersonWindows;
import static fr.ynov.librarymanagement.gui.utils.Adder.addButtonToFrame;

public class GuiManager {

    /**
     * Creates and displays the main application window.
     * <p>
     * This method initializes the primary graphical user interface for the library management system.
     * It creates the main JFrame window with a title, sets its size and layout properties,
     * and configures it to exit the application when closed.
     * </p>
     * <p>
     * The window includes a welcome header at the top and a panel with buttons in the center
     * for accessing different functional areas of the application (person management,
     * book management, and loan management).
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
     * Creates a JPanel with buttons for managing different aspects of the library system.
     * <p>
     * This method initializes a JPanel and adds buttons for managing authors/illustrators,
     * books, and loans. Each button is associated with an action listener that opens the
     * corresponding management window when clicked.
     * </p>
     *
     * @return A JPanel containing buttons for various management functions
     */
    public static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(panel, "Gérer les Auteurs/Illustrateurs", e -> openPersonWindows());
        addButtonToFrame(panel, "Gérer les Livres", e -> openBookWindow());
        addButtonToFrame(panel, "Gérer les Emprunts", e -> openLoanWindow());

        return panel;
    }
}