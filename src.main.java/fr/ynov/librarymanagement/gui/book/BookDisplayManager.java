package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;

/**
 * Manages the display of books in the library system, showing their details, status, and handling the related GUI components.
 */
public class BookDisplayManager {

    /**
     * Displays a window with a list of all books.
     * <p>
     * This method creates and displays a window showing all books in the system.
     * It loads the complete list of books from the data storage and presents them
     * in a scrollable list. Each book is displayed with its title, author, and status
     * (available or borrowed). Selecting a book in the list opens its detailed information view.
     * The window includes a refresh button that reloads the list with the most current data.
     * </p>
     */
    public static void viewBooks() {
        JFrame viewBooksFrame = new JFrame("Liste des Livres");
        viewBooksFrame.setSize(600, 500);
        viewBooksFrame.setLayout(new BorderLayout());

        loadAllBooks();
        DefaultListModel<Book> bookListModel = createBookListModel();

        JList<Book> bookList = createBookJList(bookListModel, viewBooksFrame);
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

    /**
     * Creates a DefaultListModel containing all books.
     * <p>
     * This method initializes a DefaultListModel and populates it with the list of books
     * retrieved from the BookFactory. Each book is added to the model for display in the JList.
     * </p>
     *
     * @return A DefaultListModel containing all books
     */
    private static DefaultListModel<Book> createBookListModel() {
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : BookFactory.getBookList()) {
            model.addElement(book);
        }
        return model;
    }

    /**
     * Creates a JList with a custom cell renderer for displaying books.
     * <p>
     * This method sets up a JList to display books with their title, author, and status.
     * It also adds a selection listener to handle book selection events.
     * </p>
     *
     * @param model        The model containing the list of books
     * @param parentFrame  The parent frame from which this list is opened
     * @return A JList configured to display books
     */
    private static JList<Book> createBookJList(DefaultListModel<Book> model, JFrame parentFrame) {
        JList<Book> list = new JList<>(model);
        // Set a custom cell renderer to format how each Book is displayed
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // Customize the display of each book
                if (value instanceof Book book) {
                    setText(book.getTitle() + " by " + book.getAuthor().getNameAndSurname() +
                            (book.isTaken() ? " (Emprunté)" : " (Disponible)"));
                }
                return c;
            }
        });

        // Add a selection listener to handle book selection events
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Book selectedBook = list.getSelectedValue();
                if (selectedBook != null) {
                    BookActionManager.showBookDetails(selectedBook, parentFrame);
                }
            }
        });

        return list;
    }
}