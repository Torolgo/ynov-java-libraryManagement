package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Author;
import fr.ynov.librarymanagement.domain.Illustrator;
import fr.ynov.librarymanagement.domain.Genre;
import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;

import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.Writer;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static fr.ynov.librarymanagement.factory.PersonFactory.findOrCreatePerson;
import static fr.ynov.librarymanagement.gui.UiUtils.*;


public class BookFormManager {

    /**
     * Ouvre une fenêtre pour ajouter un nouveau livre (Roman, Manga ou BD).
     * <p>
     * Cette méthode crée et affiche un formulaire pour saisir les détails du livre selon son type.
     * Les champs communs incluent titre, auteur, genre, année et nombre de pages.
     * Des champs spécifiques sont ajoutés selon le type:
     * - Roman: chapitres
     * - Manga: sous-genre
     * - BD: illustrateur et style d'illustration
     * </p>
     *
     * @param bookType Le type de livre à ajouter ("novel", "manga" ou "bd")
     */
    public static void openAddBookWindow(String bookType) {
        String windowTitle;
        Map<String, String> additionalFields = new HashMap<>();

        switch (bookType) {
            case "novel":
                windowTitle = "Ajouter un Roman";
                additionalFields.put("chapters", "Chapitres");
                break;
            case "manga":
                windowTitle = "Ajouter un Manga";
                additionalFields.put("subGenre", "Sous-genre");
                break;
            case "bd":
                windowTitle = "Ajouter une BD";
                additionalFields.put("illustrator", "Illustrateur");
                additionalFields.put("illustrationStyle", "Style d'illustration");
                break;
            default:
                throw new IllegalArgumentException("Type de livre non reconnu: " + bookType);
        }

        int rows = bookType.equals("bd") ? 8 : 7;

        createBookWindow(windowTitle, rows, fields -> {
            try {
                String title = ((JTextField)fields.get("title")).getText();
                Author author = findOrCreatePerson(((JTextField)fields.get("author")).getText(), Author.class, "");
                Genre genre = (Genre) ((JComboBox<?>)fields.get("genre")).getSelectedItem();
                int year = Integer.parseInt(((JTextField)fields.get("year")).getText());
                int pages = Integer.parseInt(((JTextField)fields.get("pages")).getText());

                String successMessage;
                String filename;

                switch (bookType) {
                    case "novel":
                        int chapters = Integer.parseInt(((JTextField)fields.get("chapters")).getText());
                        Writer.writeBookFile(
                                () -> new Novel(BookFactory.getNextAvailableBookId(), title, author, genre, year, pages, chapters),
                                "novels.json",
                                Novel.class
                        );
                        successMessage = "Roman ajouté avec succès!";
                        break;
                    case "manga":
                        String subGenre = ((JTextField)fields.get("subGenre")).getText();
                        Writer.writeBookFile(
                                () -> new Manga(BookFactory.getNextAvailableBookId(), title, author, genre, year, pages, subGenre),
                                "mangas.json",
                                Manga.class
                        );
                        successMessage = "Manga ajouté avec succès!";
                        break;
                    case "bd":
                        String illustratorName = ((JTextField)fields.get("illustrator")).getText();
                        String illustrationStyle = ((JTextField)fields.get("illustrationStyle")).getText();
                        Illustrator illustrator = findOrCreatePerson(illustratorName, Illustrator.class, illustrationStyle);
                        Writer.writeBookFile(
                                () -> new Bd(BookFactory.getNextAvailableBookId(), title, author, genre, year, pages, illustrator, illustrationStyle),
                                "bds.json",
                                Bd.class
                        );
                        successMessage = "BD ajoutée avec succès!";
                        break;
                    default:
                        throw new IllegalArgumentException("Type de livre non reconnu");
                }

                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), successMessage);
            } catch (Exception ex) {
                showError(fields.get("title").getTopLevelAncestor(), ex);
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

        JTextField titleField = createLabeledTextField(frame, "Titre");
        JTextField authorField = createLabeledTextField(frame, "Auteur");
        JComboBox<Genre> genreComboBox = createLabeledComboBox(frame, "Genre", Genre.values());
        JTextField yearField = createLabeledTextField(frame, "Année");
        JTextField pagesField = createLabeledTextField(frame, "Pages");

        Map<String, JComponent> allFields = new HashMap<>();
        allFields.put("title", titleField);
        allFields.put("author", authorField);
        allFields.put("genre", genreComboBox);
        allFields.put("year", yearField);
        allFields.put("pages", pagesField);

        Map<String, JTextField> additionalJFields = new HashMap<>();
        for (Map.Entry<String, String> entry : additionalFields.entrySet()) {
            JTextField field = createLabeledTextField(frame, entry.getValue());
            additionalJFields.put(entry.getKey(), field);
            allFields.put(entry.getKey(), field);
        }

        addButtonToFrame(frame, "Ajouter", e -> {
            try {
                onAdd.accept(allFields);
            } catch (Exception ex) {
                showError(frame, ex);
            }
        });

        frame.setVisible(true);
    }
}