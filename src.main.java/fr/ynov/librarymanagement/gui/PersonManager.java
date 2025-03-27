package fr.ynov.librarymanagement.gui;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.factory.PersonFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;

import java.util.List;

public class PersonManager {
    public static void openAuthorWindow() {
        JFrame authorFrame = new JFrame("Gérer les Auteurs et Illustrateurs");
        authorFrame.setSize(400, 300);
        authorFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAddPerson = new JButton("Ajouter une Personne");
        JButton btnViewAuthors = new JButton("Consulter les Auteurs");

        btnAddPerson.addActionListener(e -> openAddPersonTypeWindow());
        btnViewAuthors.addActionListener(e -> viewPersonsList());

        authorFrame.add(btnAddPerson);
        authorFrame.add(btnViewAuthors);
        authorFrame.setVisible(true);
    }

    private static void openAddPersonTypeWindow() {
        JFrame addPersonTypeFrame = new JFrame("Choisir le type de personne");
        addPersonTypeFrame.setSize(400, 200);
        addPersonTypeFrame.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnAddAuthor = new JButton("Ajouter un Auteur");
        JButton btnAddIllustrator = new JButton("Ajouter un Illustrateur");

        btnAddAuthor.addActionListener(e -> openAddPersonWindow(true));
        btnAddIllustrator.addActionListener(e -> openAddPersonWindow(false));

        addPersonTypeFrame.add(btnAddAuthor);
        addPersonTypeFrame.add(btnAddIllustrator);

        addPersonTypeFrame.setVisible(true);
    }

    private static void openAddPersonWindow(boolean isAuthor) {
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

        addPersonFrame.add(new JLabel("Nom:"));
        addPersonFrame.add(nameField);
        addPersonFrame.add(new JLabel("Prénom:"));
        addPersonFrame.add(surnameField);
        addPersonFrame.add(new JLabel("Nationalité:"));
        addPersonFrame.add(nationalityField);
        addPersonFrame.add(new JLabel("Date de naissance:"));
        addPersonFrame.add(dobField);
        addPersonFrame.add(new JLabel("Biographie:"));
        addPersonFrame.add(bioField);
        addPersonFrame.add(new JLabel(styleLabel));
        addPersonFrame.add(styleField);
        addPersonFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                if (isAuthor) {
                    PersonFactory.WriteAuthorFile(nameField.getText(), surnameField.getText(),
                            nationalityField.getText(), dobField.getText(), bioField.getText(), styleField.getText());
                    JOptionPane.showMessageDialog(addPersonFrame, "Auteur ajouté avec succès!");
                } else {
                    PersonFactory.WriteIllustratorFile(nameField.getText(), surnameField.getText(),
                            nationalityField.getText(), dobField.getText(), bioField.getText(), styleField.getText());
                    JOptionPane.showMessageDialog(addPersonFrame, "Illustrateur ajouté avec succès!");
                }
                addPersonFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addPersonFrame, "Erreur: " + ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addPersonFrame.setVisible(true);
    }

    private static JList<Person> getPersonJList(DefaultListModel<Person> personListModel) {
        JList<Person> personJList = new JList<>(personListModel);
        personJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Person person) {
                    String type = (person instanceof Author) ? "Auteur" : "Illustrateur";
                    setText(type + ": " + person.getNameAndSurname());
                }
                return c;
            }
        });

        // Add selection listener
        personJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Person selectedPerson = personJList.getSelectedValue();
                if (selectedPerson != null) {
                    showPersonDetails(selectedPerson);
                }
            }
        });
        return personJList;
    }

    private static void viewPersonsList() {
        JFrame viewPersonsFrame = new JFrame("Liste des Personnes");
        viewPersonsFrame.setSize(500, 500);
        viewPersonsFrame.setLayout(new BorderLayout());

        // Clear and reload all persons
        PersonFactory.clearPersonList();
        PersonFactory.ReadAuthorFile();
        PersonFactory.ReadIllustratorFile();
        List<Person> personList = PersonFactory.getPersonList();

        // Create a list model
        DefaultListModel<Person> personListModel = new DefaultListModel<>();
        for (Person person : personList) {
            personListModel.addElement(person);
        }

        // Create and configure the JList
        JList<Person> personJList = getPersonJList(personListModel);

        JScrollPane scrollPane = new JScrollPane(personJList);
        viewPersonsFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> {
            viewPersonsFrame.dispose();
            viewPersonsList();
        });
        buttonPanel.add(refreshButton);
        viewPersonsFrame.add(buttonPanel, BorderLayout.SOUTH);

        viewPersonsFrame.setVisible(true);
    }

    private static void showPersonDetails(Person person) {
        boolean isAuthor = (person instanceof Author);
        String type = isAuthor ? "Auteur" : "Illustrateur";

        JFrame detailFrame = new JFrame("Détails " + type + ": " + person.getNameAndSurname());
        detailFrame.setSize(500, 400);
        detailFrame.setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Common person details
        addDetailRow(detailsPanel, "ID", String.valueOf(person.getId()));
        addDetailRow(detailsPanel, "Nom", person.getNameAndSurname());
        addDetailRow(detailsPanel, "Date de naissance", person.getDateOfBirth());
        addDetailRow(detailsPanel, "Biographie", person.getBiography());

        // Specific details based on person type
        if (isAuthor) {
            Author author = (Author) person;
            addDetailRow(detailsPanel, "Style d'écriture", author.getWritingStyle());
        } else {
            Illustrator illustrator = (Illustrator) person;
            addDetailRow(detailsPanel, "Style d'illustration", illustrator.getIllustrationStyle());
        }

        // Edit biography panel
        JPanel editPanel = new JPanel(new BorderLayout());
        JLabel editLabel = new JLabel("Modifier la biographie:");
        JTextArea bioTextArea = new JTextArea(5, 30);
        bioTextArea.setText(person.getBiography());
        bioTextArea.setLineWrap(true);
        bioTextArea.setWrapStyleWord(true);

        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(e -> {
            String newBio = bioTextArea.getText();
            person.setBiography(newBio);

            // Save the updated biography to the appropriate JSON file
            if (isAuthor) {
                PersonFactory.updateAuthorBiography(person.getId(), newBio);
            } else {
                PersonFactory.updateIllustratorBiography(person.getId(), newBio);
            }

            JOptionPane.showMessageDialog(detailFrame, "Biographie mise à jour!");
            // Update the display
            detailFrame.dispose();
            showPersonDetails(person);
        });

        editPanel.add(editLabel, BorderLayout.NORTH);
        editPanel.add(new JScrollPane(bioTextArea), BorderLayout.CENTER);
        editPanel.add(updateButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
        detailFrame.add(editPanel, BorderLayout.SOUTH);

        detailFrame.setVisible(true);
    }

    private static void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel valueComponent = new JLabel(value);

        rowPanel.add(labelComponent);
        rowPanel.add(valueComponent);
        panel.add(rowPanel);
    }


}