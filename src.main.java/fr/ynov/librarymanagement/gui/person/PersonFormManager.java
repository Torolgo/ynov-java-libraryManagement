package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.factory.Writer;
import fr.ynov.librarymanagement.gui.utils.Creator;
import fr.ynov.librarymanagement.gui.utils.Display;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.utils.Adder.addButtonToFrame;

/**
 * Manages the creation of authors and illustrators by providing a form interface for entering and saving person details.
 */
public class PersonFormManager {

    /**
     * Opens a window to add a new Author or Illustrator.
     * <p>
     * This method creates a JFrame with fields for entering the person's details.
     * It also includes a button to save the person.
     * </p>
     *
     * @param isAuthor true if the person is an author, false if an illustrator
     */
    public static void openAddPersonWindow(boolean isAuthor) {
        JFrame addPersonFrame = new JFrame(isAuthor ? "Ajouter un Auteur" : "Ajouter un Illustrateur");
        addPersonFrame.setSize(500, 600);
        addPersonFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = Creator.createLabeledTextField(addPersonFrame, "Nom");
        JTextField surnameField = Creator.createLabeledTextField(addPersonFrame, "Prénom");
        JTextField nationalityField = Creator.createLabeledTextField(addPersonFrame, "Nationalité");
        JTextField dateOfBirthField = Creator.createLabeledTextField(addPersonFrame, "Date de naissance");
        JTextArea biographyField = Creator.createLabeledTextArea(addPersonFrame, "Biographie", 5, 20);
        JTextField styleField = Creator.createLabeledTextField(addPersonFrame,
                isAuthor ? "Style d'écriture" : "Style d'illustration");

        addButtonToFrame(addPersonFrame, "Ajouter", e -> {
            try {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String nationality = nationalityField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String biography = biographyField.getText();
                String style = styleField.getText();

                //Checks whether data has been entered for each field
                if (name.isEmpty()) {
                    throw new Exception("Le nom est obligatoire");
                }
                if (surname.isEmpty()) {
                    throw new Exception("Le prénom est obligatoire");
                }
                if (nationality.isEmpty()) {
                    throw new Exception("La nationalité est obligatoire");
                }
                if (dateOfBirth.isEmpty()) {
                    throw new Exception("La date de naissance est obligatoire");
                }
                if (biography.isEmpty()) {
                    throw new Exception("La biographie est obligatoire");
                }
                if (style.isEmpty()) {
                    throw new Exception("Le style est obligatoire");
                }

                String filename = isAuthor ? "authors.json" : "illustrators.json";
                Class<?> personClass = isAuthor ? Author.class : Illustrator.class;

                Writer.writePersonFile(name, surname, nationality, dateOfBirth, biography, style,
                        (Class)personClass, filename);

                Display.showSuccessAndClose(addPersonFrame,
                        isAuthor ? "Auteur ajouté avec succès!" : "Illustrateur ajouté avec succès!");
            } catch (Exception ex) {
                Display.showError(addPersonFrame, ex);
            }
        });

        addPersonFrame.setVisible(true);
    }
}