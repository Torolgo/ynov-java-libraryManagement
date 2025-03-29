package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Genre;
import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;

import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.Writer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static fr.ynov.librarymanagement.factory.PersonFactory.findOrCreatePerson;
import static fr.ynov.librarymanagement.gui.GuiManager.showError;


public class BookFormManager {

    /**
     * Opens a form window for adding a new manga to the library.
     * <p>
     * This method creates and displays a form with fields for entering manga details.
     * It includes fields for title, author, genre (as a dropdown), year, pages, and sub-genre.
     * When the user submits the form, it creates a new manga entry using the BookWriter
     * and displays a success message if the operation completes without errors.
     * </p>
     */
    public static void openAddMangaWindow() {
        Map<String, String> additionalFields = Map.of("subGenre", "Sous-genre");

        createBookWindow("Ajouter un Manga", 7, fields -> {
            try {
                String title = ((JTextField)fields.get("title")).getText();
                Author author = findOrCreatePerson(((JTextField)fields.get("author")).getText(), Author.class, "");
                Genre genre = (Genre) ((JComboBox<?>)fields.get("genre")).getSelectedItem();
                int year = Integer.parseInt(((JTextField)fields.get("year")).getText());
                int pages = Integer.parseInt(((JTextField)fields.get("pages")).getText());
                String subGenre = ((JTextField)fields.get("subGenre")).getText();

                Writer.writeBookFile(
                        () -> new Manga(BookFactory.getNextAvailableBookId(), title, author, genre, year, pages, subGenre),
                        "mangas.json",
                        Manga.class
                );
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "Manga ajouté avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor());
            }
        }, additionalFields);
    }

    /**
     * Opens a form window for adding a new novel to the library.
     * <p>
     * This method creates and displays a form with fields for entering novel details.
     * It includes fields for title, author, genre (as a dropdown), year, pages, and chapters.
     * When the user submits the form, it creates a new novel entry using the BookWriter
     * and displays a success message if the operation completes without errors.
     * </p>
     */
    public static void openAddNovelWindow() {
        Map<String, String> additionalFields = Map.of("chapters", "Chapitres");

        createBookWindow("Ajouter un Roman", 7, fields -> {
            try {
                String title = ((JTextField)fields.get("title")).getText();
                Author author = findOrCreatePerson(((JTextField)fields.get("author")).getText(), Author.class, "");
                Genre genre = (Genre) ((JComboBox<?>)fields.get("genre")).getSelectedItem();
                int year = Integer.parseInt(((JTextField)fields.get("year")).getText());
                int pages = Integer.parseInt(((JTextField)fields.get("pages")).getText());
                int chapters = Integer.parseInt(((JTextField)fields.get("chapters")).getText());

                Writer.writeBookFile(
                        () -> new Novel(BookFactory.getNextAvailableBookId(), title, author, genre, year, pages, chapters),
                        "novels.json",
                        Novel.class
                );
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "Roman ajouté avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor());
            }
        }, additionalFields);
    }

    /**
     * Opens a form window for adding a new BD (comic book) to the library.
     * <p>
     * This method creates and displays a form with fields for entering BD details.
     * It includes fields for title, author, genre (as a dropdown), year, pages, illustrator, and illustration style.
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
                String title = ((JTextField)fields.get("title")).getText();
                Author author = findOrCreatePerson(((JTextField)fields.get("author")).getText(), Author.class, "");

                String illustratorName = ((JTextField)fields.get("illustrator")).getText();
                String illustrationStyle = ((JTextField)fields.get("illustrationStyle")).getText();
                Illustrator illustrator = findOrCreatePerson(illustratorName, Illustrator.class, illustrationStyle);

                Genre genre = (Genre) ((JComboBox<?>)fields.get("genre")).getSelectedItem();
                int year = Integer.parseInt(((JTextField)fields.get("year")).getText());
                int pages = Integer.parseInt(((JTextField)fields.get("pages")).getText());

                Writer.writeBookFile(
                        () -> new Bd(BookFactory.getNextAvailableBookId(), title, author, genre, year, pages, illustrator, illustrationStyle),
                        "bds.json",
                        Bd.class
                );
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "BD ajoutée avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor());
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
    private static void createBookWindow(String title, int rows, Consumer<Map<String, JComponent>> onAdd,
                                         Map<String, String> additionalFields) {
        JFrame frame = new JFrame(title);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(rows, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JComboBox<Genre> genreComboBox = createGenreComboBox();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();

        addFieldsToFrame(frame, titleField, authorField, genreComboBox, yearField, pagesField);

        Map<String, JComponent> allFields = new HashMap<>();
        allFields.put("title", titleField);
        allFields.put("author", authorField);
        allFields.put("genre", genreComboBox);
        allFields.put("year", yearField);
        allFields.put("pages", pagesField);

        Map<String, JTextField> additionalJFields = new HashMap<>();
        for (Map.Entry<String, String> entry : additionalFields.entrySet()) {
            JTextField field = new JTextField();
            frame.add(new JLabel(entry.getValue() + ":"));
            frame.add(field);
            additionalJFields.put(entry.getKey(), field);
            allFields.put(entry.getKey(), field);
        }

        JButton addButton = new JButton("Ajouter");
        frame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                onAdd.accept(allFields);
            } catch (Exception ex) {
                showError(frame);
            }
        });

        frame.setVisible(true);
    }

    /**
     * Creates a dropdown combobox containing all values of the Genre enum.
     *
     * @return A JComboBox containing all Genre values
     */
    private static JComboBox<Genre> createGenreComboBox() {
        return new JComboBox<>(Genre.values());
    }

    /**
     * Adds common fields to the book form frame.
     * <p>
     * This method adds labels and input fields for title, author, genre, year, and pages
     * to the provided JFrame. It is used to set up the layout of the book form window.
     * </p>
     *
     * @param frame        The JFrame to which the fields will be added
     * @param titleField   The text field for the book title
     * @param authorField  The text field for the author's name
     * @param genreComboBox The dropdown for the book genre
     * @param yearField    The text field for the publication year
     * @param pagesField   The text field for the number of pages
     */
    private static void addFieldsToFrame(JFrame frame, JTextField titleField, JTextField authorField,
                                         JComboBox<Genre> genreComboBox, JTextField yearField, JTextField pagesField) {
        frame.add(new JLabel("Titre:"));
        frame.add(titleField);
        frame.add(new JLabel("Auteur:"));
        frame.add(authorField);
        frame.add(new JLabel("Genre:"));
        frame.add(genreComboBox);
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
}