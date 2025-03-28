package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Genre;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.factory.book.BookWriter;

import static fr.ynov.librarymanagement.factory.person.PersonFactory.findOrCreateAuthor;
import static fr.ynov.librarymanagement.factory.person.PersonFactory.findOrCreateIllustrator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.util.Map;
import java.util.function.Consumer;


public class BookFormManager {

    /**
     * Opens a form window for adding a new manga to the library.
     * <p>
     * This method creates and displays a form with fields for entering manga details.
     * It includes fields for title, author, genre, year, pages, and sub-genre.
     * When the user submits the form, it creates a new manga entry using the BookWriter
     * and displays a success message if the operation completes without errors.
     * </p>
     */
    public static void openAddMangaWindow() {
        Map<String, String> additionalFields = Map.of("subGenre", "Sous-genre");

        createBookWindow("Ajouter un Manga", 7, fields -> {
            try {
                String title = fields.get("title").getText();
                Author author = findOrCreateAuthor(fields.get("author").getText());
                Genre genre = Genre.valueOf(fields.get("genre").getText().toUpperCase());
                int year = Integer.parseInt(fields.get("year").getText());
                int pages = Integer.parseInt(fields.get("pages").getText());
                String subGenre = fields.get("subGenre").getText();

                BookWriter.writeMangaFile(title, author, genre, year, pages, subGenre);
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "Manga ajouté avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor(), ex);
            }
        }, additionalFields);
    }

    /**
     * Opens a form window for adding a new novel to the library.
     * <p>
     * This method creates and displays a form with fields for entering novel details.
     * It includes fields for title, author, genre, year, pages, and chapters.
     * When the user submits the form, it creates a new novel entry using the BookWriter
     * and displays a success message if the operation completes without errors.
     * </p>
     */
    public static void openAddNovelWindow() {
        Map<String, String> additionalFields = Map.of("chapters", "Chapitres");

        createBookWindow("Ajouter un Roman", 7, fields -> {
            try {
                String title = fields.get("title").getText();
                Author author = findOrCreateAuthor(fields.get("author").getText());
                Genre genre = Genre.valueOf(fields.get("genre").getText().toUpperCase());
                int year = Integer.parseInt(fields.get("year").getText());
                int pages = Integer.parseInt(fields.get("pages").getText());
                int chapters = Integer.parseInt(fields.get("chapters").getText());

                BookWriter.writeNovelFile(title, author, genre, year, pages, chapters);
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "Roman ajouté avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor(), ex);
            }
        }, additionalFields);
    }

    /**
     * Opens a form window for adding a new BD (comic book) to the library.
     * <p>
     * This method creates and displays a form with fields for entering BD details.
     * It includes fields for title, author, genre, year, pages, illustrator, and illustration style.
     * When the user submits the form, it creates a new BD entry using the BookWriter
     * and displays a success message if the operation completes without errors.
     * </p>
     */
    public static void openAddBdWindow() {
        Map<String, String> additionalFields = Map.of(
                "illustrator", "Illustrateur",
                "illustrationStyle", "Style d'illustration"
        );

        createBookWindow("Ajouter une BD", 8, fields -> {
            try {
                String title = fields.get("title").getText();
                Author author = findOrCreateAuthor(fields.get("author").getText());

                String illustratorName = fields.get("illustrator").getText();
                String illustrationStyle = fields.get("illustrationStyle").getText();
                Illustrator illustrator = findOrCreateIllustrator(illustratorName, illustrationStyle);

                Genre genre = Genre.valueOf(fields.get("genre").getText().toUpperCase());
                int year = Integer.parseInt(fields.get("year").getText());
                int pages = Integer.parseInt(fields.get("pages").getText());

                BookWriter.writeBdFile(title, author, genre, year, pages, illustrator, illustrationStyle);
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "BD ajoutée avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor(), ex);
            }
        }, additionalFields);
    }

    /**
     * Creates a form window for adding a book with specified fields.
     * <p>
     * This method sets up a JFrame with text fields for common book attributes
     * and additional fields as specified in the additionalFields map. It also
     * handles the submission of the form and calls the provided consumer function
     * to process the input data.
     * </p>
     *
     * @param title            The title of the window
     * @param rows             The number of rows in the layout
     * @param onAdd            A consumer function to handle the form submission
     * @param additionalFields A map of additional fields to be included in the form
     */
    private static void createBookWindow(String title, int rows, Consumer<Map<String, JTextField>> onAdd,
                                         Map<String, String> additionalFields) {
        JFrame frame = new JFrame(title);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(rows, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();

        addFieldsToFrame(frame, titleField, authorField, genreField, yearField, pagesField);

        Map<String, JTextField> allFields = Map.of(
                "title", titleField,
                "author", authorField,
                "genre", genreField,
                "year", yearField,
                "pages", pagesField
        );

        Map<String, JTextField> additionalJFields = new java.util.HashMap<>();
        for (Map.Entry<String, String> entry : additionalFields.entrySet()) {
            JTextField field = new JTextField();
            frame.add(new JLabel(entry.getValue() + ":"));
            frame.add(field);
            additionalJFields.put(entry.getKey(), field);
        }

        JButton addButton = new JButton("Ajouter");
        frame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                // Combiner les champs communs et additionnels
                Map<String, JTextField> combinedFields = new java.util.HashMap<>(allFields);
                combinedFields.putAll(additionalJFields);

                // Appeler le consumer avec tous les champs
                onAdd.accept(combinedFields);
            } catch (Exception ex) {
                showError(frame, ex);
            }
        });

        frame.setVisible(true);
    }

    /**
     * Adds common fields to the book form frame.
     * <p>
     * This method adds labels and text fields for title, author, genre, year, and pages
     * to the provided JFrame. It is used to set up the layout of the book form window.
     * </p>
     *
     * @param frame        The JFrame to which the fields will be added
     * @param titleField   The text field for the book title
     * @param authorField  The text field for the author's name
     * @param genreField   The text field for the book genre
     * @param yearField    The text field for the publication year
     * @param pagesField   The text field for the number of pages
     */
    private static void addFieldsToFrame(JFrame frame, JTextField titleField, JTextField authorField,
                                         JTextField genreField, JTextField yearField, JTextField pagesField) {
        frame.add(new JLabel("Titre:"));
        frame.add(titleField);
        frame.add(new JLabel("Auteur:"));
        frame.add(authorField);
        frame.add(new JLabel("Genre:"));
        frame.add(genreField);
        frame.add(new JLabel("Année:"));
        frame.add(yearField);
        frame.add(new JLabel("Pages:"));
        frame.add(pagesField);
    }

    /**
     * Displays a success message and closes the frame.
     * <p>
     * This method shows a message dialog with the provided message and then disposes of the frame.
     * </p>
     *
     * @param frame   The JFrame to be closed
     * @param message The success message to be displayed
     */
    static void showSuccessAndClose(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
        frame.dispose();
    }

    /**
     * Displays an error message in a dialog.
     * <p>
     * This method shows a message dialog with the provided exception message.
     * </p>
     *
     * @param frame The JFrame from which the error is displayed
     * @param ex    The exception whose message will be shown
     */
    static void showError(JFrame frame, Exception ex) {
        JOptionPane.showMessageDialog(frame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}