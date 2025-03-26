package fr.ynov.librarymanagement.gui;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.factory.PersonFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PersonManager {
    private static void openAddPersonTypeWindow() {
        JFrame addPersonTypeFrame = new JFrame("Choisir le type de personne");
        addPersonTypeFrame.setSize(400, 200);
        addPersonTypeFrame.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnAddAuthor = new JButton("Ajouter un Auteur");
        JButton btnAddIllustrator = new JButton("Ajouter un Illustrateur");

        btnAddAuthor.addActionListener(e -> openAddAuthorWindow());
        btnAddIllustrator.addActionListener(e -> openAddIllustratorWindow());

        addPersonTypeFrame.add(btnAddAuthor);
        addPersonTypeFrame.add(btnAddIllustrator);

        addPersonTypeFrame.setVisible(true);
    }

    private static void openAddAuthorWindow() {
        JFrame addAuthorFrame = new JFrame("Ajouter un Auteur");
        addAuthorFrame.setSize(400, 400);
        addAuthorFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField nationalityField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField bioField = new JTextField();
        JTextField writingStyleField = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Removed ID field since it's now automatic
        addAuthorFrame.add(new JLabel("Nom:"));
        addAuthorFrame.add(nameField);
        addAuthorFrame.add(new JLabel("Prénom:"));
        addAuthorFrame.add(surnameField);
        addAuthorFrame.add(new JLabel("Nationalité:"));
        addAuthorFrame.add(nationalityField);
        addAuthorFrame.add(new JLabel("Date de naissance:"));
        addAuthorFrame.add(dobField);
        addAuthorFrame.add(new JLabel("Biographie:"));
        addAuthorFrame.add(bioField);
        addAuthorFrame.add(new JLabel("Style d'écriture:"));
        addAuthorFrame.add(writingStyleField);
        addAuthorFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                PersonFactory.WriteAuthorFile(nameField.getText(), surnameField.getText(), nationalityField.getText(), dobField.getText(), bioField.getText(), writingStyleField.getText());
                JOptionPane.showMessageDialog(addAuthorFrame, "Auteur ajouté avec succès!");
                addAuthorFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addAuthorFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addAuthorFrame.setVisible(true);
    }

    private static void openAddIllustratorWindow() {
        JFrame addIllustratorFrame = new JFrame("Ajouter un Illustrateur");
        addIllustratorFrame.setSize(400, 400);
        addIllustratorFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField nationalityField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField bioField = new JTextField();
        JTextField illustrationStyleField = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Removed ID field since it's now automatic
        addIllustratorFrame.add(new JLabel("Nom:"));
        addIllustratorFrame.add(nameField);
        addIllustratorFrame.add(new JLabel("Prénom:"));
        addIllustratorFrame.add(surnameField);
        addIllustratorFrame.add(new JLabel("Nationalité:"));
        addIllustratorFrame.add(nationalityField);
        addIllustratorFrame.add(new JLabel("Date de naissance:"));
        addIllustratorFrame.add(dobField);
        addIllustratorFrame.add(new JLabel("Biographie:"));
        addIllustratorFrame.add(bioField);
        addIllustratorFrame.add(new JLabel("Style d'illustration:"));
        addIllustratorFrame.add(illustrationStyleField);
        addIllustratorFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                PersonFactory.WriteIllustratorFile(nameField.getText(), surnameField.getText(), nationalityField.getText(), dobField.getText(), bioField.getText(), illustrationStyleField.getText());
                JOptionPane.showMessageDialog(addIllustratorFrame, "Illustrateur ajouté avec succès!");
                addIllustratorFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addIllustratorFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addIllustratorFrame.setVisible(true);
    }

    private static void viewAuthors() {
        viewPersonsList();
    }

    private static JList<Person> getPersonJList(DefaultListModel<Person> personListModel) {
        JList<Person> personJList = new JList<>(personListModel);
        personJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Person) {
                    Person person = (Person) value;
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

    public static void openAuthorWindow() {
        JFrame authorFrame = new JFrame("Gérer les Auteurs et Illustrateurs");
        authorFrame.setSize(400, 300);
        authorFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAddPerson = new JButton("Ajouter une Personne");
        JButton btnViewAuthors = new JButton("Consulter les Auteurs");

        btnAddPerson.addActionListener(e -> openAddPersonTypeWindow());
        btnViewAuthors.addActionListener(e -> viewAuthors());

        authorFrame.add(btnAddPerson);
        authorFrame.add(btnViewAuthors);
        authorFrame.setVisible(true);
    }

    public static Author findAuthorByName(String name) {
        PersonFactory.clearPersonList();
        PersonFactory.ReadAuthorFile();

        for (Person person : PersonFactory.getPersonList()) {
            if (person instanceof Author && person.getNameAndSurname().toLowerCase().contains(name.toLowerCase())) {
                return (Author) person;
            }
        }
        return null;
    }

    public static Illustrator findIllustratorByName(String name) {
        PersonFactory.clearPersonList();
        PersonFactory.ReadIllustratorFile();

        for (Person person : PersonFactory.getPersonList()) {
            if (person instanceof Illustrator && person.getNameAndSurname().toLowerCase().contains(name.toLowerCase())) {
                return (Illustrator) person;
            }
        }
        return null;
    }
}