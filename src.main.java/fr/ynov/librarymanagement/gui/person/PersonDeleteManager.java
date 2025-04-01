package fr.ynov.librarymanagement.gui.person;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Person;
import fr.ynov.librarymanagement.factory.FilesManagement;
import fr.ynov.librarymanagement.factory.PersonFactory;
import fr.ynov.librarymanagement.factory.Deleter;
import fr.ynov.librarymanagement.gui.utils.Display;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.DefaultListCellRenderer;

import java.awt.BorderLayout;
import java.awt.Component;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;
import static fr.ynov.librarymanagement.factory.Reader.loadAllPersons;

public class PersonDeleteManager extends FilesManagement {


    /**
     * Opens a window for deleting Authors or Illustrators.
     * <p>
     * This method loads all persons and books data, then creates a window that displays
     * a list of all available persons (authors and illustrators). The user can select a person
     * from the list and delete them using the delete button. Before deletion, the system checks
     * if the selected person is referenced in any books and prevents deletion if that's the case.
     * </p>
     */
    public static void openDeletePersonWindow() {
        loadAllPersons();
        loadAllBooks();

        JFrame deletePersonFrame = new JFrame("Supprimer un Auteur/Illustrateur");
        deletePersonFrame.setSize(450, 350);
        deletePersonFrame.setLayout(new BorderLayout());

        DefaultListModel<Person> personListModel = new DefaultListModel<>();
        for (Person person : PersonFactory.getPersonList()) {
            personListModel.addElement(person);
        }

        JList<Person> personList = getPersonJList(personListModel);

        JScrollPane scrollPane = new JScrollPane(personList);
        deletePersonFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = getJPanel(personList, deletePersonFrame, personListModel);
        deletePersonFrame.add(buttonPanel, BorderLayout.SOUTH);

        deletePersonFrame.setVisible(true);
    }

    /**
     * Creates and configures a JList for displaying Person objects.
     * <p>
     * This method sets up a JList with a custom cell renderer that displays each person
     * with their ID, type (Author or Illustrator), and full name. The renderer formats
     * each list item in the format: "ID - Type: Name Surname".
     * </p>
     *
     * @param personListModel The DefaultListModel containing the Person objects to be displayed
     * @return A configured JList component ready to display Person objects
     */
    private static JList<Person> getPersonJList(DefaultListModel<Person> personListModel) {
        JList<Person> personList = new JList<>(personListModel);

        // Set a custom cell renderer to format how each Person is displayed
        personList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                        boolean isSelected, boolean cellHasFocus) {
                // Get the default component from the parent renderer
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Person person) {
                    String type = (person instanceof Author) ? "Auteur" : "Illustrateur";
                    setText(person.getId() + " - " + type + ": " + person.getNameAndSurname());
                }
                return c;
            }
        });

        return personList;
    }

    /**
     * Creates a panel with a delete button for the person deletion window.
     * <p>
     * This method configures a JPanel containing a delete button with the appropriate action listener.
     * The listener handles the deletion process including: checking if the person is referenced in books,
     * displaying confirmation dialogs, executing the deletion operation, updating the UI,
     * and showing appropriate messages to the user.
     * </p>
     *
     * @param personList The JList showing the available persons to delete
     * @param deletePersonFrame The parent frame that contains the list
     * @param personListModel The list model that needs to be updated after deletion
     * @return A configured JPanel with the delete button
     */
    private static JPanel getJPanel(JList<Person> personList, JFrame deletePersonFrame, DefaultListModel<Person> personListModel) {
        JButton deleteButton = new JButton("Supprimer");

        // Configure the action listener for the delete operation
        deleteButton.addActionListener(e -> {
            Person selectedPerson = personList.getSelectedValue();

            if (selectedPerson != null) {
                boolean isReferenced = Deleter.isPersonReferencedInBooks(selectedPerson);

                if (isReferenced) {
                    Display.showError(deletePersonFrame, new Exception(
                        "Cette personne est référencée dans un ou plusieurs livres et ne peut pas être supprimée."));
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        deletePersonFrame,
                    "Êtes-vous sûr de vouloir supprimer " +
                    (selectedPerson instanceof Author ? "l'auteur " : "l'illustrateur ") +
                    selectedPerson.getNameAndSurname() + "?",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Deleter.deletePerson(selectedPerson);
                        personListModel.removeElement(selectedPerson);
                        Display.showSuccess(deletePersonFrame, "Supprimé avec succès!");

                    } catch (Exception ex) {
                        Display.showError(deletePersonFrame, ex);
                    }
                }
            } else {
                Display.showError(deletePersonFrame, new Exception("Aucune personne sélectionnée"));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        return buttonPanel;
    }
}