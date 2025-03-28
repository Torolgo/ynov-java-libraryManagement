package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.factory.Writer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.GuiManager.showError;

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
        String personType = isAuthor ? "Auteur" : "Illustrateur";
        String styleLabel = isAuthor ? "Style d'écriture:" : "Style d'illustration:";

        JFrame addPersonFrame = new JFrame("Ajouter un " + personType);
        addPersonFrame.setSize(400, 400);
        addPersonFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField nationalityField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField bioField = new JTextField();
        JTextField styleField = new JTextField();
        JButton addButton = new JButton("Ajouter");

        addFieldsToFrame(addPersonFrame, nameField, surnameField, nationalityField, dobField, bioField, styleField, styleLabel);
        addPersonFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                savePerson(isAuthor, addPersonFrame, nameField, surnameField, nationalityField,
                        dobField, bioField, styleField);
            } catch (Exception ex) {
                showError(addPersonFrame);
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
        frame.add(new JLabel("Nom:"));
        frame.add(nameField);
        frame.add(new JLabel("Prénom:"));
        frame.add(surnameField);
        frame.add(new JLabel("Nationalité:"));
        frame.add(nationalityField);
        frame.add(new JLabel("Date de naissance:"));
        frame.add(dobField);
        frame.add(new JLabel("Biographie:"));
        frame.add(bioField);
        frame.add(new JLabel(styleLabel));
        frame.add(styleField);
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