package fr.ynov.librarymanagement.gui.book;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.function.Consumer;

public class BookManager {

    /**
     * Opens the main book management window.
     * <p>
     * This method creates and displays a window with options for managing books in the library.
     * It includes buttons for adding new books and viewing the existing book collection.
     * The window uses a vertical grid layout with two main action buttons.
     * </p>
     */
    public static void openBookWindow() {
        JFrame bookFrame = new JFrame("Gérer les Livres");
        bookFrame.setSize(400, 300);
        bookFrame.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(bookFrame, "Ajouter un Livre", e -> openAddBookTypeWindow());
        addButtonToFrame(bookFrame, "Consulter les Livres", e -> BookDisplayManager.viewBooks());

        bookFrame.setVisible(true);
    }

    /**
     * Opens a window for selecting the type of book to add.
     * <p>
     * This method creates and displays a window with buttons for different book types
     * (Manga, Novel, and Comic Book). When a button is clicked, it opens the corresponding
     * form for adding that specific type of book to the library. The window uses a simple
     * vertical grid layout with three action buttons.
     * </p>
     */
    private static void openAddBookTypeWindow() {
        JFrame addBookTypeFrame = new JFrame("Choisir le type de livre");
        addBookTypeFrame.setSize(400, 200);
        addBookTypeFrame.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(addBookTypeFrame, "Ajouter un Manga", e -> BookFormManager.openAddMangaWindow());
        addButtonToFrame(addBookTypeFrame, "Ajouter un Roman", e -> BookFormManager.openAddNovelWindow());
        addButtonToFrame(addBookTypeFrame, "Ajouter une BD", e -> BookFormManager.openAddBdWindow());

        addBookTypeFrame.setVisible(true);
    }

    /**
     * Adds a button to the specified frame with the given action.
     * <p>
     * This method creates a JButton with the specified text and adds it to the provided frame.
     * The button is configured to perform the specified action when clicked.
     * </p>
     *
     * @param frame      The JFrame to which the button will be added
     * @param buttonText The text to display on the button
     * @param action     The action to perform when the button is clicked
     */
    static void addButtonToFrame(JFrame frame, String buttonText, Consumer<java.awt.event.ActionEvent> action) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action::accept);
        frame.add(button);
    }
}