package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.gui.utils.Display;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;

import static fr.ynov.librarymanagement.factory.Updater.updateBiography;
import static fr.ynov.librarymanagement.gui.utils.Adder.addDetailRow;

/**
 * Manages person information display and interaction, showing details specific to Authors and Illustrators and enabling biography editing directly from the view.
 */
public class PersonActionManager {

    /**
     * Displays the details of a selected person in a new window.
     * <p>
     * This method creates a JFrame to show detailed information about a person,
     * including their ID, name, date of birth, biography, and specific attributes
     * based on whether they are an Author or Illustrator. It also provides an
     * option to edit the biography.
     * </p>
     *
     * @param person The Person object whose details are to be displayed
     */
    public static void showPersonDetails(Person person) {
        JFrame detailFrame = new JFrame("Détails de la personne: " + person.getNameAndSurname());
        detailFrame.setSize(500, 600);
        detailFrame.setLayout(new BorderLayout());

        boolean isAuthor = person instanceof Author;
        JPanel detailsPanel = createDetailsPanel(person, isAuthor);

        JPanel editPanel = createBioEditPanel(person, detailFrame);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
        detailFrame.add(editPanel, BorderLayout.SOUTH);
        detailFrame.setVisible(true);
    }

    /**
     * Creates a panel with detailed information about a person.
     * <p>
     * This method builds a panel containing all relevant information about the provided person.
     * It displays common details (ID, name, date of birth, biography) and specific details
     * depending on whether the person is an Author or Illustrator.
     * </p>
     *
     * @param person    The Person object whose details are to be displayed
     * @param isAuthor  Indicates if the person is an Author (true) or Illustrator (false)
     * @return A JPanel containing the person's details
     */
    private static JPanel createDetailsPanel(Person person, boolean isAuthor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addDetailRow(panel, "ID", String.valueOf(person.getId()));
        addDetailRow(panel, "Nom", person.getNameAndSurname());
        addDetailRow(panel, "Date de naissance", person.getDateOfBirth());
        addDetailRow(panel, "Nationalité", person.getNationality());
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
     * This method sets up a panel with a text area for editing the biography
     * and a button to save the changes. It also provides feedback upon successful update.
     * </p>
     *
     * @param person      The Person object whose biography is to be edited
     * @param parentFrame The parent JFrame from which this edit panel is opened
     * @return A JPanel containing the biography edit components
     */
    private static JPanel createBioEditPanel(Person person, JFrame parentFrame) {
        JPanel editPanel = new JPanel(new BorderLayout());
        JLabel editLabel = new JLabel("Modifier la biographie:");
        JTextArea bioTextArea = new JTextArea(5, 30);
        bioTextArea.setText(person.getBiography());
        bioTextArea.setLineWrap(true);
        bioTextArea.setWrapStyleWord(true);

        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(e -> {
            updateBiography(person, bioTextArea.getText());
            Display.showSuccessAndClose(parentFrame, "Biographie mise à jour!");
            showPersonDetails(person);
        });

        editPanel.add(editLabel, BorderLayout.NORTH);
        editPanel.add(new JScrollPane(bioTextArea), BorderLayout.CENTER);
        editPanel.add(updateButton, BorderLayout.SOUTH);

        return editPanel;
    }
}