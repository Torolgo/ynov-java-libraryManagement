package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Bd;
import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.domain.Manga;
import fr.ynov.librarymanagement.domain.Novel;
import fr.ynov.librarymanagement.factory.Updater;

import static fr.ynov.librarymanagement.gui.uiutils.Adder.addDetailRow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;


public class BookActionManager {

    /**
     * Displays a detailed view of a book in a new window.
     * <p>
     * This method creates a new frame showing all details of the selected book.
     * It includes a button that allows the user to borrow or return the book
     * depending on its current status. The details are displayed in a scrollable panel.
     * </p>
     *
     * @param book         The book object whose details will be displayed
     * @param parentFrame  The parent frame from which this detail window is opened
     */
    public static void showBookDetails(Book book, JFrame parentFrame) {
        JFrame detailFrame = new JFrame("Détails du livre: " + book.getTitle());
        detailFrame.setSize(500, 600);
        detailFrame.setLayout(new BorderLayout());

        JPanel detailsPanel = createDetailsPanel(book);

        JPanel buttonPanel = new JPanel();
        JButton borrowButton = new JButton(book.isTaken() ? "Retourner" : "Emprunter");
        borrowButton.addActionListener(e -> handleBorrowReturn(book, detailFrame, parentFrame));
        buttonPanel.add(borrowButton);

        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
        detailFrame.add(buttonPanel, BorderLayout.SOUTH);
        detailFrame.setVisible(true);
    }

    /**
     * Creates a panel with detailed information about a book.
     * <p>
     * This method builds a panel containing all relevant information about the provided book.
     * It displays common details for all book types (title, author, genre, etc.) and
     * additional specific details depending on the book type (Novel, Bd, or Manga).
     * </p>
     *
     * @param book  The book object whose details will be displayed in the panel
     * @return      A JPanel containing formatted book details in a vertical layout
     */
    private static JPanel createDetailsPanel(Book book) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addDetailRow(panel, "ID", String.valueOf(book.getId()));
        addDetailRow(panel, "Titre", book.getTitle());
        addDetailRow(panel, "Auteur", book.getAuthor().getNameAndSurname());
        addDetailRow(panel, "Genre", book.getGenre().toString());
        addDetailRow(panel, "Année", String.valueOf(book.getYear()));
        addDetailRow(panel, "Pages", String.valueOf(book.getPages()));
        addDetailRow(panel, "Statut", book.isTaken() ? "Emprunté" : "Disponible");

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

    /**
     * Handles the action of borrowing or returning a book.
     * <p>
     * This method updates the book's status and notifies the user about the action taken.
     * It also refreshes the main book display after the action is completed.
     * </p>
     *
     * @param book         The book object to be borrowed or returned
     * @param detailFrame  The frame displaying the book details
     * @param parentFrame  The parent frame from which this action is initiated
     */
    private static void handleBorrowReturn(Book book, JFrame detailFrame, JFrame parentFrame) {
        if (book.isTaken()) {
            book.returnBook();
        } else {
            book.takeBook();
        }

        Updater.updateBookStatus(book);
        JOptionPane.showMessageDialog(detailFrame,
                book.isTaken() ? "Livre emprunté avec succès!" : "Livre retourné avec succès!");

        detailFrame.dispose();
        parentFrame.dispose();
        BookDisplayManager.viewBooks();
    }
}