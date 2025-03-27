package fr.ynov.librarymanagement.gui;

import fr.ynov.librarymanagement.domain.*;

import fr.ynov.librarymanagement.factory.BookFactory;
import static fr.ynov.librarymanagement.factory.PersonFactory.findOrCreateAuthor;
import static fr.ynov.librarymanagement.factory.PersonFactory.findOrCreateIllustrator;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.Map;
import java.util.function.Consumer;




public class BookManager {
    public static void openBookWindow() {
        JFrame bookFrame = new JFrame("Gérer les Livres");
        bookFrame.setSize(400, 300);
        bookFrame.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(bookFrame, "Ajouter un Livre", e -> openAddBookTypeWindow());
        addButtonToFrame(bookFrame, "Consulter les Livres", e -> viewBooks());

        bookFrame.setVisible(true);
    }

    public static void openAddBookTypeWindow() {
        JFrame addBookTypeFrame = new JFrame("Choisir le type de livre");
        addBookTypeFrame.setSize(400, 200);
        addBookTypeFrame.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(addBookTypeFrame, "Ajouter un Manga", e -> openAddMangaWindow());
        addButtonToFrame(addBookTypeFrame, "Ajouter un Roman", e -> openAddNovelWindow());
        addButtonToFrame(addBookTypeFrame, "Ajouter une BD", e -> openAddBdWindow());

        addBookTypeFrame.setVisible(true);
    }

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

                BookFactory.WriteMangasFile(title, author, genre, year, pages, subGenre);
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "Manga ajouté avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor(), ex);
            }
        }, additionalFields);
    }

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

                BookFactory.WriteNovelsFile(title, author, genre, year, pages, chapters);
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "Roman ajouté avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor(), ex);
            }
        }, additionalFields);
    }

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

                BookFactory.WriteBdsFile(title, author, genre, year, pages, illustrator, illustrationStyle);
                showSuccessAndClose((JFrame)fields.get("title").getTopLevelAncestor(), "BD ajoutée avec succès!");
            } catch (Exception ex) {
                showError((JFrame)fields.get("title").getTopLevelAncestor(), ex);
            }
        }, additionalFields);
    }

    private static void addButtonToFrame(JFrame frame, String buttonText, Consumer<java.awt.event.ActionEvent> action) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action::accept);
        frame.add(button);
    }

    // Generic method to create book windows

    private static void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel valueComponent = new JLabel(value);

        rowPanel.add(labelComponent);
        rowPanel.add(valueComponent);
        panel.add(rowPanel);
    }

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


    private static void showSuccessAndClose(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
        frame.dispose();
    }

    private static void showError(JFrame frame, Exception ex) {
        JOptionPane.showMessageDialog(frame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private static void showBookDetails(Book book, JFrame parentFrame) {
        JFrame detailFrame = new JFrame("Détails du livre: " + book.getTitle());
        detailFrame.setSize(500, 600);
        detailFrame.setLayout(new BorderLayout());

        // Create details panel
        JPanel detailsPanel = createDetailsPanel(book);

        // Create borrow/return button
        JPanel buttonPanel = new JPanel();
        JButton borrowButton = new JButton(book.isTaken() ? "Retourner" : "Emprunter");
        borrowButton.addActionListener(e -> handleBorrowReturn(book, detailFrame, parentFrame));
        buttonPanel.add(borrowButton);

        // Assemble frame
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
        detailFrame.add(buttonPanel, BorderLayout.SOUTH);
        detailFrame.setVisible(true);
    }

    public static void viewBooks() {
        JFrame viewBooksFrame = new JFrame("Consulter les Livres");
        viewBooksFrame.setSize(500, 500);
        viewBooksFrame.setLayout(new BorderLayout());

        // Load all books
        loadAllBooks();

        // Create list model and JList
        DefaultListModel<Book> bookListModel = createBookListModel();
        JList<Book> bookList = createBookJList(bookListModel, viewBooksFrame);
        JScrollPane scrollPane = new JScrollPane(bookList);

        // Add refresh button
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> {
            viewBooksFrame.dispose();
            viewBooks();
        });
        buttonPanel.add(refreshButton);

        // Assemble frame
        viewBooksFrame.add(scrollPane, BorderLayout.CENTER);
        viewBooksFrame.add(buttonPanel, BorderLayout.SOUTH);
        viewBooksFrame.setVisible(true);
    }

    private static void loadAllBooks() {
        BookFactory.clearBookList();
        BookFactory.ReadNovelsFile();
        BookFactory.ReadBdsFile();
        BookFactory.ReadMangasFile();
    }

    private static DefaultListModel<Book> createBookListModel() {
        //à deplacer dans une factory
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : BookFactory.getBookList()) {
            model.addElement(book);
        }
        return model;
    }

    private static void createBookWindow(String title, int rows, Consumer<Map<String, JTextField>> onAdd,
                                         Map<String, String> additionalFields) {
        JFrame frame = new JFrame(title);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(rows, 2, 10, 10));

        // Common fields for all books
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();

        // Add common fields
        addFieldsToFrame(frame, titleField, authorField, genreField, yearField, pagesField);

        // Create a map to store all fields including additional ones
        Map<String, JTextField> allFields = Map.of(
                "title", titleField,
                "author", authorField,
                "genre", genreField,
                "year", yearField,
                "pages", pagesField
        );

        // Add additional fields specific to book type
        Map<String, JTextField> additionalJFields = new java.util.HashMap<>();
        for (Map.Entry<String, String> entry : additionalFields.entrySet()) {
            JTextField field = new JTextField();
            frame.add(new JLabel(entry.getValue() + ":"));
            frame.add(field);
            additionalJFields.put(entry.getKey(), field);
        }

        // Add button
        JButton addButton = new JButton("Ajouter");
        frame.add(addButton);

        // Set action listener to button
        addButton.addActionListener(e -> {
            try {
                // Combine common and additional fields
                Map<String, JTextField> combinedFields = new java.util.HashMap<>(allFields);
                combinedFields.putAll(additionalJFields);

                // Call the provided consumer with all fields
                onAdd.accept(combinedFields);
            } catch (Exception ex) {
                showError(frame, ex);
            }
        });

        frame.setVisible(true);
    }

    private static JList<Book> createBookJList(DefaultListModel<Book> model, JFrame parentFrame) {
        JList<Book> list = new JList<>(model);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Book book) {
                    setText(book.getTitle() + " by " + book.getAuthor().getNameAndSurname() +
                            (book.isTaken() ? " (Emprunté)" : " (Disponible)"));
                }
                return c;
            }
        });

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Book selectedBook = list.getSelectedValue();
                if (selectedBook != null) {
                    showBookDetails(selectedBook, parentFrame);
                }
            }
        });

        return list;
    }

    private static JPanel createDetailsPanel(Book book) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Common book details
        addDetailRow(panel, "Titre", book.getTitle());
        addDetailRow(panel, "Auteur", book.getAuthor().getNameAndSurname());
        addDetailRow(panel, "Genre", book.getGenre().toString());
        addDetailRow(panel, "Année", String.valueOf(book.getYear()));
        addDetailRow(panel, "Pages", String.valueOf(book.getPages()));
        addDetailRow(panel, "Statut", book.isTaken() ? "Emprunté" : "Disponible");

        // Specific details based on book type
        switch (book) {
            case Novel novel -> addDetailRow(panel, "Chapitres", String.valueOf(novel.getChapters()));
            case Bd bd -> {
                addDetailRow(panel, "Illustrateur", bd.getIllustrator().getNameAndSurname());
                addDetailRow(panel, "Style d'illustration", bd.getIllustrationStyle());
            }
            case Manga manga -> addDetailRow(panel, "Sous-genre", manga.getSubGenre());
            default -> {}
        }

        return panel;
    }

    private static void handleBorrowReturn(Book book, JFrame detailFrame, JFrame parentFrame) {
        if (book.isTaken()) {
            book.returnBook();
        } else {
            book.takeBook();
        }

        BookFactory.updateBookStatus(book);
        JOptionPane.showMessageDialog(detailFrame,
                book.isTaken() ? "Livre emprunté avec succès!" : "Livre retourné avec succès!");

        detailFrame.dispose();
        parentFrame.dispose();
        viewBooks(); // Refresh the book list view
    }
}