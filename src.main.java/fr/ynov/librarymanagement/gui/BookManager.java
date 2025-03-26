package fr.ynov.librarymanagement.gui;

import fr.ynov.librarymanagement.domain.*;
import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.PersonFactory;

import javax.swing.*;
import java.awt.*;

import static fr.ynov.librarymanagement.gui.PersonManager.findAuthorByName;
import static fr.ynov.librarymanagement.gui.PersonManager.findIllustratorByName;

public class BookManager {
    public static void openBookWindow() {
        JFrame bookFrame = new JFrame("Gérer les Livres");
        bookFrame.setSize(400, 300);
        bookFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAddBook = new JButton("Ajouter un Livre");
        JButton btnViewBooks = new JButton("Consulter les Livres");

        btnAddBook.addActionListener(e -> openAddBookTypeWindow());
        btnViewBooks.addActionListener(e -> viewBooks());

        bookFrame.add(btnAddBook);
        bookFrame.add(btnViewBooks);
        bookFrame.setVisible(true);
    }

    public static void openAddBookTypeWindow() {
        JFrame addBookTypeFrame = new JFrame("Choisir le type de livre");
        addBookTypeFrame.setSize(400, 200);
        addBookTypeFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAddManga = new JButton("Ajouter un Manga");
        JButton btnAddNovel = new JButton("Ajouter un Roman");
        JButton btnAddBd = new JButton("Ajouter une BD");

        btnAddManga.addActionListener(e -> openAddMangaWindow());
        btnAddNovel.addActionListener(e -> openAddNovelWindow());
        btnAddBd.addActionListener(e -> openAddBdWindow());

        addBookTypeFrame.add(btnAddManga);
        addBookTypeFrame.add(btnAddNovel);
        addBookTypeFrame.add(btnAddBd);

        addBookTypeFrame.setVisible(true);
    }

    public static void openAddMangaWindow() {
        JFrame addMangaFrame = new JFrame("Ajouter un Manga");
        addMangaFrame.setSize(400, 400);
        addMangaFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();
        JTextField subGenreField = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Removed ID field since it's now automatic
        addMangaFrame.add(new JLabel("Titre:"));
        addMangaFrame.add(titleField);
        addMangaFrame.add(new JLabel("Auteur:"));
        addMangaFrame.add(authorField);
        addMangaFrame.add(new JLabel("Genre:"));
        addMangaFrame.add(genreField);
        addMangaFrame.add(new JLabel("Année:"));
        addMangaFrame.add(yearField);
        addMangaFrame.add(new JLabel("Pages:"));
        addMangaFrame.add(pagesField);
        addMangaFrame.add(new JLabel("Sous-genre:"));
        addMangaFrame.add(subGenreField);
        addMangaFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();

                // Find or create author
                String authorName = authorField.getText();
                Author author = findAuthorByName(authorName);
                if (author == null) {
                    // If author doesn't exist, create a new one
                    PersonFactory.WriteAuthorFile(authorName, "", "", "", "", "");
                    author = findAuthorByName(authorName); // Fetch the newly created author with assigned ID
                }

                Genre genre = Genre.valueOf(genreField.getText().toUpperCase());
                int year = Integer.parseInt(yearField.getText());
                int pages = Integer.parseInt(pagesField.getText());
                String subGenre = subGenreField.getText();

                BookFactory.WriteMangasFile(title, author, genre, year, pages, subGenre);
                JOptionPane.showMessageDialog(addMangaFrame, "Manga ajouté avec succès!");
                addMangaFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addMangaFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addMangaFrame.setVisible(true);
    }

    public static void openAddNovelWindow() {
        JFrame addNovelFrame = new JFrame("Ajouter un Roman");
        addNovelFrame.setSize(400, 400);
        addNovelFrame.setLayout(new GridLayout(7, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();
        JTextField chaptersField = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Removed ID field since it's now automatic
        addNovelFrame.add(new JLabel("Titre:"));
        addNovelFrame.add(titleField);
        addNovelFrame.add(new JLabel("Auteur:"));
        addNovelFrame.add(authorField);
        addNovelFrame.add(new JLabel("Genre:"));
        addNovelFrame.add(genreField);
        addNovelFrame.add(new JLabel("Année:"));
        addNovelFrame.add(yearField);
        addNovelFrame.add(new JLabel("Pages:"));
        addNovelFrame.add(pagesField);
        addNovelFrame.add(new JLabel("Chapitres:"));
        addNovelFrame.add(chaptersField);
        addNovelFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();

                // Find or create author
                String authorName = authorField.getText();
                Author author = findAuthorByName(authorName);
                if (author == null) {
                    // If author doesn't exist, create a new one
                    PersonFactory.WriteAuthorFile(authorName, "", "", "", "", "");
                    author = findAuthorByName(authorName); // Fetch the newly created author with assigned ID
                }

                Genre genre = Genre.valueOf(genreField.getText().toUpperCase());
                int year = Integer.parseInt(yearField.getText());
                int pages = Integer.parseInt(pagesField.getText());
                int chapters = Integer.parseInt(chaptersField.getText());

                BookFactory.WriteNovelsFile(title, author, genre, year, pages, chapters);
                JOptionPane.showMessageDialog(addNovelFrame, "Roman ajouté avec succès!");
                addNovelFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addNovelFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addNovelFrame.setVisible(true);
    }

    public static void openAddBdWindow() {
        JFrame addBdFrame = new JFrame("Ajouter une BD");
        addBdFrame.setSize(400, 400);
        addBdFrame.setLayout(new GridLayout(8, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField pagesField = new JTextField();
        JTextField illustratorField = new JTextField();
        JTextField illustrationStyleField = new JTextField();
        JButton addButton = new JButton("Ajouter");

        // Removed ID field since it's now automatic
        addBdFrame.add(new JLabel("Titre:"));
        addBdFrame.add(titleField);
        addBdFrame.add(new JLabel("Auteur:"));
        addBdFrame.add(authorField);
        addBdFrame.add(new JLabel("Genre:"));
        addBdFrame.add(genreField);
        addBdFrame.add(new JLabel("Année:"));
        addBdFrame.add(yearField);
        addBdFrame.add(new JLabel("Pages:"));
        addBdFrame.add(pagesField);
        addBdFrame.add(new JLabel("Illustrateur:"));
        addBdFrame.add(illustratorField);
        addBdFrame.add(new JLabel("Style d'illustration:"));
        addBdFrame.add(illustrationStyleField);
        addBdFrame.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();

                // Find or create author
                String authorName = authorField.getText();
                Author author = findAuthorByName(authorName);
                if (author == null) {
                    // If author doesn't exist, create a new one
                    PersonFactory.WriteAuthorFile(authorName, "", "", "", "", "");
                    author = findAuthorByName(authorName); // Fetch the newly created author with assigned ID
                }

                // Find or create illustrator
                String illustratorName = illustratorField.getText();
                String illustrationStyle = illustrationStyleField.getText();
                Illustrator illustrator = findIllustratorByName(illustratorName);
                if (illustrator == null) {
                    // If illustrator doesn't exist, create a new one
                    PersonFactory.WriteIllustratorFile(illustratorName, "", "", "", "", illustrationStyle);
                    illustrator = findIllustratorByName(illustratorName); // Fetch the newly created illustrator with assigned ID
                }

                Genre genre = Genre.valueOf(genreField.getText().toUpperCase());
                int year = Integer.parseInt(yearField.getText());
                int pages = Integer.parseInt(pagesField.getText());

                BookFactory.WriteBdsFile(title, author, genre, year, pages, illustrator, illustrationStyle);
                JOptionPane.showMessageDialog(addBdFrame, "BD ajoutée avec succès!");
                addBdFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addBdFrame, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addBdFrame.setVisible(true);
    }

    public static void viewBooks() {
        JFrame viewBooksFrame = new JFrame("Consulter les Livres");
        viewBooksFrame.setSize(500, 500);
        viewBooksFrame.setLayout(new BorderLayout());

        // Clear existing book list and reload all books
        BookFactory.clearBookList();
        BookFactory.ReadNovelsFile();
        BookFactory.ReadBdsFile();
        BookFactory.ReadMangasFile();

        // Create a list model for the books
        DefaultListModel<Book> bookListModel = new DefaultListModel<>();
        for (Book book : BookFactory.getBookList()) {
            bookListModel.addElement(book);
        }

        // Create and configure the JList
        JList<Book> bookList = new JList<>(bookListModel);
        bookList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Book) {
                    Book book = (Book) value;
                    setText(book.getTitle() + " by " + book.getAuthor().getNameAndSurname() +
                            (book.isTaken() ? " (Emprunté)" : " (Disponible)"));
                }
                return c;
            }
        });

        // Add selection listener to display book details when clicked
        bookList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Book selectedBook = bookList.getSelectedValue();
                if (selectedBook != null) {
                    showBookDetails(selectedBook, viewBooksFrame);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(bookList);

        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> {
            viewBooksFrame.dispose();
            viewBooks();
        });
        buttonPanel.add(refreshButton);

        viewBooksFrame.add(scrollPane, BorderLayout.CENTER);
        viewBooksFrame.add(buttonPanel, BorderLayout.SOUTH);
        viewBooksFrame.setVisible(true);
    }

    private static void showBookDetails(Book book, JFrame parentFrame) {
        JFrame detailFrame = new JFrame("Détails du livre: " + book.getTitle());
        detailFrame.setSize(500, 600);
        detailFrame.setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Common book details
        addDetailRow(detailsPanel, "Titre", book.getTitle());
        addDetailRow(detailsPanel, "Auteur", book.getAuthor().getNameAndSurname());
        addDetailRow(detailsPanel, "Genre", book.getGenre().toString());
        addDetailRow(detailsPanel, "Année", String.valueOf(book.getYear()));
        addDetailRow(detailsPanel, "Pages", String.valueOf(book.getPages()));
        addDetailRow(detailsPanel, "Statut", book.isTaken() ? "Emprunté" : "Disponible");

        // Specific details based on book type
        switch (book) {
            case Novel novel -> addDetailRow(detailsPanel, "Chapitres", String.valueOf(novel.getChapters()));
            case Bd bd -> {
                addDetailRow(detailsPanel, "Illustrateur", bd.getIllustrator().getNameAndSurname());
                addDetailRow(detailsPanel, "Style d'illustration", bd.getIllustrationStyle());
            }
            case Manga manga -> addDetailRow(detailsPanel, "Sous-genre", manga.getSubGenre());
            default -> {
            }
        }

        // Buttons for actions
        JPanel buttonPanel = new JPanel();
        JButton borrowButton = new JButton(book.isTaken() ? "Retourner" : "Emprunter");
        borrowButton.addActionListener(e -> {
            if (book.isTaken()) {
                book.returnBook();
                BookFactory.updateBookStatus(book);
                JOptionPane.showMessageDialog(detailFrame, "Livre retourné avec succès!");
            } else {
                book.takeBook();
                BookFactory.updateBookStatus(book);
                JOptionPane.showMessageDialog(detailFrame, "Livre emprunté avec succès!");
            }
            detailFrame.dispose();
            parentFrame.dispose();
            viewBooks(); // Refresh the book list view
        });
        buttonPanel.add(borrowButton);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
        detailFrame.add(buttonPanel, BorderLayout.SOUTH);
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