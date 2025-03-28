package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.book.BookFactory;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

public class BookDisplayManager {

    /**
     * Displays a window with a list of all books.
     * <p>
     * This method creates a new JFrame that contains a JList of all books.
     * It also includes a button to refresh the list.
     * </p>
     */
    public static void viewBooks() {
        JFrame viewBooksFrame = new JFrame("Consulter les Livres");
        viewBooksFrame.setSize(500, 500);
        viewBooksFrame.setLayout(new BorderLayout());

        BookFactory.loadAllBooks();

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
     * This method populates the model with all books from the BookFactory.
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
     * Creates a JList with a custom cell renderer for displaying book details.
     * <p>
     * This method sets up the JList to display book titles, authors, and availability status.
     * </p>
     *
     * @param model       The DefaultListModel containing the books
     * @param parentFrame The parent JFrame for context
     * @return A JList configured to display book details
     */
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
                    BookActionManager.showBookDetails(selectedBook, parentFrame);
                }
            }
        });

        return list;
    }

    /**
     * Adds a row to the details panel with a label and value.
     * <p>
     * This method creates a new JPanel for the row and adds it to the provided panel.
     * </p>
     *
     * @param panel  The parent panel to which the row will be added
     * @param label  The label for the row
     * @param value  The value for the row
     */
    static void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel valueComponent = new JLabel(value);

        rowPanel.add(labelComponent);
        rowPanel.add(valueComponent);
        panel.add(rowPanel);
    }
}