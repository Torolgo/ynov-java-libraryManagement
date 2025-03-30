package fr.ynov.librarymanagement.gui.person;

import javax.swing.JFrame;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.uiutils.Adder.addButtonToFrame;

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
    public static void openPersonWindows() {
        JFrame authorFrame = new JFrame("Gérer les Auteurs et Illustrateurs");
        authorFrame.setSize(400, 300);
        authorFrame.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(authorFrame, "Ajouter un Auteur/Illustrateur", e -> openAddPersonTypeWindow());
        addButtonToFrame(authorFrame, "Consulter les Auteurs/Illustrateurs", e -> PersonDisplayManager.viewPersonsList());

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

        addButtonToFrame(addPersonTypeFrame, "Ajouter un Auteur", e -> PersonFormManager.openAddPersonWindow(true));
        addButtonToFrame(addPersonTypeFrame, "Ajouter un Illustrateur", e -> PersonFormManager.openAddPersonWindow(false));

        addPersonTypeFrame.setVisible(true);
    }
}