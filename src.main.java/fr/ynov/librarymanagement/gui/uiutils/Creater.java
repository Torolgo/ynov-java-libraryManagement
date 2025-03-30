package fr.ynov.librarymanagement.gui.uiutils;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Container;

public class Creater {

    /**
     * Creates a labeled text field and adds it to the container.
     * <p>
     * This utility method creates a JLabel with the specified text followed by a colon,
     * and a JTextField. The label and text field are added to the specified container,
     * allowing for easy layout management.
     * </p>
     *
     * @param container The container to add the label and text field to (such as JFrame, JPanel, etc.)
     * @param labelText The text to display on the label before the text field
     * @return          The created JTextField instance, allowing for further customization or data retrieval
     */
    public static JTextField createLabeledTextField(Container container, String labelText) {
        container.add(new JLabel(labelText + ":"));
        JTextField textField = new JTextField();
        container.add(textField);
        return textField;
    }

    /**
     * Creates a labeled combo box and adds it to the container.
     * <p>
     * This utility method creates a JLabel with the specified text followed by a colon,
     * and a JComboBox with the provided items. The label and combo box are added to the
     * specified container, allowing for easy layout management.
     * </p>
     *
     * @param container The container to add the label and combo box to (such as JFrame, JPanel, etc.)
     * @param labelText The text to display on the label before the combo box
     * @param items     The array of items to populate the combo box
     * @return          The created JComboBox instance, allowing for further customization or data retrieval
     */
    public static <T> JComboBox<T> createLabeledComboBox(Container container, String labelText, T[] items) {
        container.add(new JLabel(labelText + ":"));
        JComboBox<T> comboBox = new JComboBox<>(items);
        container.add(comboBox);
        return comboBox;
    }

    /**
     * Creates a labeled text area and adds it to the container.
     * <p>
     * This utility method creates a JLabel with the specified text followed by a colon,
     * and a JTextArea with the specified dimensions. The text area is configured with line wrapping
     * and word wrapping enabled, and is placed inside a JScrollPane to provide scrolling capability
     * when the content exceeds the visible area. Both the label and the scroll pane containing
     * the text area are added to the provided container.
     * </p>
     *
     * @param container The container to add the label and text area to (such as JFrame, JPanel, etc.)
     * @param labelText The text to display on the label before the text area
     * @param rows      The number of rows to use for the text area
     * @param columns   The number of columns to use for the text area
     * @return          The created JTextArea instance, allowing for further customization or data retrieval
     */
    public static JTextArea createLabeledTextArea(Container container, String labelText, int rows, int columns) {
        container.add(new JLabel(labelText + ":"));
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        container.add(scrollPane);
        return textArea;
    }
}

