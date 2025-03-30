package fr.ynov.librarymanagement.gui.book;

import javax.swing.JFrame;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.gui.uiutils.Adder.addButtonToFrame;

public class BookManager {

    /**
     * Opens the main book management window.
     * <p>
     * This method creates and displays a window with options for managing books in the library.
     * It includes buttons for adding new books, viewing and deleting existing books.
     * The window uses a vertical grid layout with three main action buttons.
     * </p>
     */
    public static void openBookWindow() {
        JFrame bookFrame = new JFrame("Gérer les Livres");
        bookFrame.setSize(400, 300);
        bookFrame.setLayout(new GridLayout(4, 1, 10, 10));

        addButtonToFrame(bookFrame, "Ajouter un Livre", e -> openAddBookTypeWindow());
        addButtonToFrame(bookFrame, "Consulter les Livres", e -> BookDisplayManager.viewBooks());
        addButtonToFrame(bookFrame, "Supprimer un Livre", e -> BookDeleteManager.openDeleteBookWindow());

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

        addButtonToFrame(addBookTypeFrame, "Ajouter un Manga", e -> BookFormManager.openAddBookWindow("manga"));
        addButtonToFrame(addBookTypeFrame, "Ajouter un Roman", e -> BookFormManager.openAddBookWindow("novel"));
        addButtonToFrame(addBookTypeFrame, "Ajouter une BD", e -> BookFormManager.openAddBookWindow("bd"));

        addBookTypeFrame.setVisible(true);
    }
}