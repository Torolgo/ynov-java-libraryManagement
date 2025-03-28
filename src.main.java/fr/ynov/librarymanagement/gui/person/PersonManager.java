package fr.ynov.librarymanagement.gui.person;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

public class PersonManager {

    /**
     * Opens the main window for managing Authors and Illustrators.
     * <p>
     * This method creates and displays a window with options for managing persons in the library system.
     * It provides buttons for adding new persons (either Authors or Illustrators) and viewing
     * the complete list of all persons. When buttons are clicked, they trigger their respective
     * management windows for further operations.
     * </p>
     */
    public static void openAuthorWindow() {
        JFrame authorFrame = new JFrame("Gérer les Auteurs et Illustrateurs");
        authorFrame.setSize(400, 300);
        authorFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAddPerson = new JButton("Ajouter une Personne");
        JButton btnViewAuthors = new JButton("Consulter les Auteurs");

        btnAddPerson.addActionListener(e -> openAddPersonTypeWindow());
        btnViewAuthors.addActionListener(e -> PersonDisplayManager.viewPersonsList());

        authorFrame.add(btnAddPerson);
        authorFrame.add(btnViewAuthors);
        authorFrame.setVisible(true);
    }

    /**
     * Opens a selection window for choosing the type of person to add.
     * <p>
     * This method creates and displays a window with two buttons that allow the user to select
     * whether they want to add an Author or an Illustrator to the system. Based on the selection,
     * it will open the appropriate form with fields specific to that person type. The window uses
     * a vertical grid layout with two buttons.
     * </p>
     */
    private static void openAddPersonTypeWindow() {
        JFrame addPersonTypeFrame = new JFrame("Choisir le type de personne");
        addPersonTypeFrame.setSize(400, 200);
        addPersonTypeFrame.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnAddAuthor = new JButton("Ajouter un Auteur");
        JButton btnAddIllustrator = new JButton("Ajouter un Illustrateur");

        btnAddAuthor.addActionListener(e -> PersonFormManager.openAddPersonWindow(true));
        btnAddIllustrator.addActionListener(e -> PersonFormManager.openAddPersonWindow(false));

        addPersonTypeFrame.add(btnAddAuthor);
        addPersonTypeFrame.add(btnAddIllustrator);

        addPersonTypeFrame.setVisible(true);
    }
}