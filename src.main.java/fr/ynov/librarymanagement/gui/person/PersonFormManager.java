package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.factory.Writer;

import javax.swing.*;

import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.UiUtils.*;

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

        JTextField nameField = createLabeledTextField(addPersonFrame, "Nom");
        JTextField surnameField = createLabeledTextField(addPersonFrame, "Prénom");
        JTextField nationalityField = createLabeledTextField(addPersonFrame, "Nationalité");
        JTextField dateOfBirthField = createLabeledTextField(addPersonFrame, "Date de naissance");
        JTextArea biographyField = createLabeledTextArea(addPersonFrame, "Biographie", 5, 20);
        JTextField styleField = createLabeledTextField(addPersonFrame,
                isAuthor ? "Style d'écriture" : "Style d'illustration");

        addButtonToFrame(addPersonFrame, "Ajouter", e -> {
            try {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String nationality = nationalityField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String biography = biographyField.getText();
                String style = styleField.getText();

                String filename = isAuthor ? "authors.json" : "illustrators.json";
                Class<?> personClass = isAuthor ? Author.class : Illustrator.class;

                Writer.writePersonFile(name, surname, nationality, dateOfBirth, biography, style,
                        (Class)personClass, filename);

                showSuccessAndClose(addPersonFrame,
                        isAuthor ? "Auteur ajouté avec succès!" : "Illustrateur ajouté avec succès!");
            } catch (Exception ex) {
                showError(addPersonFrame, ex);
            }
        });

        addPersonFrame.setVisible(true);
    }

    /**
     * Adds form fields to the person creation frame.
     * <p>
     * This helper method populates the frame with labeled text fields for entering
     * person details, including name, surname, nationality, date of birth, biography,
     * and a style field that varies based on the person type (writing style for authors
     * or illustration style for illustrators).
     * </p>
     *
     * @param frame             The JFrame to which the fields will be added
     * @param nameField         Text field for the person's name
     * @param surnameField      Text field for the person's surname
     * @param nationalityField  Text field for the person's nationality
     * @param dobField          Text field for the person's date of birth
     * @param bioField          Text field for the person's biography
     * @param styleField        Text field for the person's style (writing or illustration)
     * @param styleLabel        The label text for the style field
     */
    private static void addFieldsToFrame(JFrame frame, JTextField nameField, JTextField surnameField,
                                         JTextField nationalityField, JTextField dobField,
                                         JTextField bioField, JTextField styleField, String styleLabel) {
        createLabeledTextField(frame, "Nom");
        createLabeledTextField(frame, "Prénom");
        createLabeledTextField(frame, "Nationalité");
        createLabeledTextField(frame, "Date de naissance");
        createLabeledTextField(frame, "Biographie");
        createLabeledTextField(frame, styleLabel);
    }

    /**
     * Saves a new person (Author or Illustrator) to the system.
     * <p>
     * This method collects data from form fields and passes it to the appropriate writer method
     * based on whether the person is an Author or an Illustrator. Upon successful addition,
     * it displays a confirmation message and closes the input form.
     * </p>
     *
     * @param isAuthor          True if the person is an Author, false if an Illustrator
     * @param frame             The parent frame for displaying messages and closing upon completion
     * @param nameField         Text field containing the person's name
     * @param surnameField      Text field containing the person's surname
     * @param nationalityField  Text field containing the person's nationality
     * @param dobField          Text field containing the person's date of birth
     * @param bioField          Text field containing the person's biography
     * @param styleField        Text field containing the person's specific style
     *                          (writing style for Authors, illustration style for Illustrators)
     */
    private static void savePerson(boolean isAuthor, JFrame frame, JTextField nameField,
                                   JTextField surnameField, JTextField nationalityField,
                                   JTextField dobField, JTextField bioField, JTextField styleField) {
        if (isAuthor) {
            Writer.writePersonFile(nameField.getText(), surnameField.getText(), nationalityField.getText(),
                    dobField.getText(), bioField.getText(), styleField.getText(), Author.class, "authors.json");
            JOptionPane.showMessageDialog(frame, "Auteur ajouté avec succès!");

        } else {
            Writer.writePersonFile(nameField.getText(), surnameField.getText(), nationalityField.getText(),
                    dobField.getText(), bioField.getText(), styleField.getText(), Illustrator.class, "illustrators.json");
            JOptionPane.showMessageDialog(frame, "Illustrateur ajouté avec succès!");
        }
        frame.dispose();
    }
}