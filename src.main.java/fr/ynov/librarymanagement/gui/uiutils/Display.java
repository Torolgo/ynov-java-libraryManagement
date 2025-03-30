package fr.ynov.librarymanagement.gui.uiutils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Component;

public class Display {

    /**
     * Displays an error message dialog with the specified exception message.
     * <p>
     * This method creates a modal dialog that shows the error message to the user.
     * </p>
     *
     * @param parent    The parent component for the dialog (can be null)
     * @param exception The exception whose message will be displayed
     */
    public static void showError(Component parent, Exception exception) {
        JOptionPane.showMessageDialog(parent,
                "Erreur: " + exception.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a success message dialog with the specified message.
     * <p>
     * This method creates a modal dialog that shows the success message to the user.
     * </p>
     *
     * @param parent  The parent component for the dialog (can be null)
     * @param message The success message to display
     */
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent,
                message,
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a success message dialog and closes the specified JFrame.
     * <p>
     * This method shows a success message and then disposes of the given JFrame.
     * </p>
     *
     * @param frame   The JFrame to close after displaying the message
     * @param message The success message to display
     */
    public static void showSuccessAndClose(JFrame frame, String message) {
        showSuccess(frame, message);
        frame.dispose();
    }
}
