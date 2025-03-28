package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.factory.PersonFactory;


import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import java.util.List;

import static fr.ynov.librarymanagement.factory.Reader.loadAllPersons;


public class PersonDisplayManager {

    /**
     * Displays a window with a list of all persons (Authors and Illustrators).
     * <p>
     * This method creates and displays a window showing all persons in the system.
     * It loads the complete list of persons from the data storage and presents them
     * in a scrollable list. Each person is displayed with their type (Author or Illustrator)
     * and name. Selecting a person in the list opens their detailed information view.
     * The window includes a refresh button that reloads the list with the most current data.
     * </p>
     */
    public static void viewPersonsList() {
        JFrame viewPersonsFrame = new JFrame("Liste des Personnes");
        viewPersonsFrame.setSize(500, 500);
        viewPersonsFrame.setLayout(new BorderLayout());

        loadAllPersons();
        List<Person> personList = PersonFactory.getPersonList();

        DefaultListModel<Person> personListModel = new DefaultListModel<>();
        for (Person person : personList) {
            personListModel.addElement(person);
        }

        JList<Person> personJList = createPersonJList(personListModel);

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

    /**
     * Creates a JList with a custom cell renderer for displaying persons.
     * <p>
     * This method sets up a JList to display persons with their type (Author or Illustrator)
     * and name. It also adds a selection listener to handle person selection events.
     * </p>
     *
     * @param personListModel The model containing the list of persons
     * @return A JList configured to display persons
     */
    private static JList<Person> createPersonJList(DefaultListModel<Person> personListModel) {
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

        personJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Person selectedPerson = personJList.getSelectedValue();
                if (selectedPerson != null) {
                    PersonActionManager.showPersonDetails(selectedPerson);
                }
            }
        });
        return personJList;
    }

    /**
     * Adds a detail row to a panel with a label and value.
     * <p>
     * This method creates a new row in the specified panel, containing a label and its corresponding value.
     * The label is styled with bold font for better visibility.
     * </p>
     *
     * @param panel  The panel to which the detail row will be added
     * @param label  The label text to display
     * @param value  The value text to display
     */
    static void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel valueComponent = new JLabel(value);

        rowPanel.add(labelComponent);
        rowPanel.add(valueComponent);
        panel.add(rowPanel);
    }
}