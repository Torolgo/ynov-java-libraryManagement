package fr.ynov.librarymanagement.gui.book;

import fr.ynov.librarymanagement.domain.Book;
import fr.ynov.librarymanagement.factory.BookFactory;
import fr.ynov.librarymanagement.factory.Deleter;
import fr.ynov.librarymanagement.factory.FilesManagement;
import fr.ynov.librarymanagement.gui.utils.Display;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Component;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;

/**
 * Manages book deletion operations by providing a user interface to select and delete books with confirmation from the library system.
 */
public class BookDeleteManager extends FilesManagement {
    /**
     * Opens a window for deleting books.
     * <p>
     * This method loads all books data, then creates a window that displays a list
     * of all available books. The user can select a book from the list and delete it
     * using the delete button. A confirmation dialog is shown before the actual deletion.
     * </p>
     */
    public static void openDeleteBookWindow() {
        loadAllBooks();

        JFrame deleteBookFrame = new JFrame("Supprimer un Livre");
        deleteBookFrame.setSize(400, 300);
        deleteBookFrame.setLayout(new BorderLayout());

        DefaultListModel<Book> bookListModel = new DefaultListModel<>();
        for (Book book : BookFactory.getBookList()) {
            bookListModel.addElement(book);
        }

        JList<Book> bookList = getBookJList(bookListModel);

        JScrollPane scrollPane = new JScrollPane(bookList);
        deleteBookFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = getJPanel(bookList, deleteBookFrame, bookListModel);
        deleteBookFrame.add(buttonPanel, BorderLayout.SOUTH);

        deleteBookFrame.setVisible(true);
    }

    /**
     * Creates and configures a JList for displaying Book objects.
     * <p>
     * This method sets up a JList with a custom cell renderer that displays each book
     * with its ID, title, and author's full name. The renderer formats each list item
     * in the format: "ID - Title - Author Name Surname".
     * </p>
     *
     * @param bookListModel The DefaultListModel containing the Book objects to be displayed
     * @return A configured JList component ready to display Book objects
     */
    private static JList<Book> getBookJList(DefaultListModel<Book> bookListModel) {
        JList<Book> bookList = new JList<>(bookListModel);

        // Set a custom cell renderer to format how each Book is displayed
        bookList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                         boolean isSelected, boolean cellHasFocus) {
                // Get the default component from the parent renderer
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Book book) {
                    setText(book.getId() + " - " + book.getTitle() + " - " + book.getAuthor().getNameAndSurname());
                }
                return c;
            }
        });
        return bookList;
    }

    /**
     * Creates a panel with a delete button for the book deletion window.
     * <p>
     * This method configures a JPanel containing a delete button with the appropriate action listener.
     * The listener handles the deletion process including: displaying confirmation dialogs,
     * executing the deletion operation, updating the UI, and showing appropriate messages to the user.
     * </p>
     *
     * @param bookList The JList showing the available books to delete
     * @param deleteBookFrame The parent frame that contains the list
     * @param bookListModel The list model that needs to be updated after deletion
     * @return A configured JPanel with the delete button
     */
    private static JPanel getJPanel(JList<Book> bookList, JFrame deleteBookFrame, DefaultListModel<Book> bookListModel) {
        JButton deleteButton = new JButton("Supprimer");

        // Configure the action listener for the delete operation
        deleteButton.addActionListener(e -> {
            Book selectedBook = bookList.getSelectedValue();

            if (selectedBook != null) {
                // Check if the book is referenced in any loans
                int confirm = JOptionPane.showConfirmDialog(
                        deleteBookFrame,
                    "Êtes-vous sûr de vouloir supprimer le livre:\n" + selectedBook.getTitle() + "?",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Deleter.deleteBook(selectedBook);
                        bookListModel.removeElement(selectedBook);
                        Display.showSuccess(deleteBookFrame, "Livre supprimé avec succès!");

                    } catch (Exception ex) {
                        Display.showError(deleteBookFrame, ex);
                    }
                }
            } else {
                Display.showError(deleteBookFrame, new Exception("Aucun livre sélectionné"));
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        return buttonPanel;
    }
}