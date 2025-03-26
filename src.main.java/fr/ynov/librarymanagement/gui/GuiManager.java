package fr.ynov.librarymanagement.gui;

import javax.swing.*;
import java.awt.*;

import static fr.ynov.librarymanagement.gui.BookManager.openBookWindow;
import static fr.ynov.librarymanagement.gui.LoanManager.openLoanWindow;
import static fr.ynov.librarymanagement.gui.PersonManager.openAuthorWindow;

public class GuiManager {

    private static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        JButton btnAuteur = new JButton("AUTEUR");
        JButton btnLivre = new JButton("LIVRE");
        JButton btnEmprunt = new JButton("EMPRUNT");

        Font font = new Font("Arial", Font.BOLD, 14);
        btnAuteur.setFont(font);
        btnLivre.setFont(font);
        btnEmprunt.setFont(font);

        btnLivre.addActionListener(e -> openBookWindow());
        btnAuteur.addActionListener(e -> openAuthorWindow());
        btnEmprunt.addActionListener(e -> openLoanWindow());

        panel.add(btnAuteur);
        panel.add(btnLivre);
        panel.add(btnEmprunt);
        return panel;
    }

    public static void GuiInterfaceManager() {
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
