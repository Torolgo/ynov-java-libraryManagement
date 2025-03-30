package fr.ynov.librarymanagement.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;

import static fr.ynov.librarymanagement.gui.UiUtils.getJPanel;

public class GuiManager {
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
}
