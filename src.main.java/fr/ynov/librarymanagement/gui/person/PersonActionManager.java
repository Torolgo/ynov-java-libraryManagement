package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.factory.person.PersonUpdater;

import static fr.ynov.librarymanagement.gui.person.PersonDisplayManager.addDetailRow;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

public class PersonActionManager {

    /**
     * Displays a detailed view of a person (Author or Illustrator) in a new window.
     * <p>
     * This method creates a new frame showing all details of the selected person.
     * It includes a button that allows the user to update the biography of the person.
     * The details are displayed in a scrollable panel.
     * </p>
     *
     * @param person The person object whose details will be displayed
     */
    public static void showPersonDetails(Person person) {
        boolean isAuthor = (person instanceof Author);
        String type = isAuthor ? "Auteur" : "Illustrateur";

        JFrame detailFrame = new JFrame("Détails " + type + ": " + person.getNameAndSurname());
        detailFrame.setSize(500, 400);
        detailFrame.setLayout(new BorderLayout());

        JPanel detailsPanel = createDetailsPanel(person, isAuthor);

        JPanel editPanel = createBioEditPanel(person, isAuthor, detailFrame);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
        detailFrame.add(editPanel, BorderLayout.SOUTH);

        detailFrame.setVisible(true);
    }

    /**
     * Creates a panel with detailed information about a person.
     * <p>
     * This method builds a panel containing all relevant information about the provided person.
     * It displays common details for both Author and Illustrator.
     * </p>
     *
     * @param person    The person object whose details will be displayed in the panel
     * @param isAuthor  True if the person is an Author, false if an Illustrator
     * @return A JPanel containing the details of the person
     */
    private static JPanel createDetailsPanel(Person person, boolean isAuthor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addDetailRow(panel, "ID", String.valueOf(person.getId()));
        addDetailRow(panel, "Nom", person.getNameAndSurname());
        addDetailRow(panel, "Date de naissance", person.getDateOfBirth());
        addDetailRow(panel, "Biographie", person.getBiography());

        if (isAuthor) {
            Author author = (Author) person;
            addDetailRow(panel, "Style d'écriture", author.getWritingStyle());
        } else {
            Illustrator illustrator = (Illustrator) person;
            addDetailRow(panel, "Style d'illustration", illustrator.getIllustrationStyle());
        }

        return panel;
    }

    /**
     * Creates a panel for editing the biography of a person.
     * <p>
     * This method provides a text area for the user to modify the biography
     * and a button to save the changes.
     * </p>
     *
     * @param person      The person object whose biography will be edited
     * @param isAuthor    True if the person is an Author, false if an Illustrator
     * @param parentFrame The parent frame for context
     * @return A JPanel containing the biography edit components
     */
    private static JPanel createBioEditPanel(Person person, boolean isAuthor, JFrame parentFrame) {
        JPanel editPanel = new JPanel(new BorderLayout());
        JLabel editLabel = new JLabel("Modifier la biographie:");
        JTextArea bioTextArea = new JTextArea(5, 30);
        bioTextArea.setText(person.getBiography());
        bioTextArea.setLineWrap(true);
        bioTextArea.setWrapStyleWord(true);

        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(e -> {
            updateBiography(person, isAuthor, bioTextArea.getText());
            JOptionPane.showMessageDialog(parentFrame, "Biographie mise à jour!");
            parentFrame.dispose();
            showPersonDetails(person);
        });

        editPanel.add(editLabel, BorderLayout.NORTH);
        editPanel.add(new JScrollPane(bioTextArea), BorderLayout.CENTER);
        editPanel.add(updateButton, BorderLayout.SOUTH);

        return editPanel;
    }

    /**
     * Updates the biography of a person and saves it to the database.
     * <p>
     * This method modifies the biography of the person and updates it in the database
     * using the PersonUpdater class.
     * </p>
     *
     * @param person      The person object whose biography will be updated
     * @param isAuthor    True if the person is an Author, false if an Illustrator
     * @param newBio      The new biography text
     */
    private static void updateBiography(Person person, boolean isAuthor, String newBio) {
        person.setBiography(newBio);
        if (isAuthor) {
            PersonUpdater.updateAuthorBiography(person.getId(), newBio);
        } else {
            PersonUpdater.updateIllustratorBiography(person.getId(), newBio);
        }
    }
}