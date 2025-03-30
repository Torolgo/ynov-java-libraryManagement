package fr.ynov.librarymanagement.gui.uiutils;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

public class Adder {
    /**
     * Creates a button with the specified text and action, then adds it to the container.
     * <p>
     * This utility method simplifies the process of creating, configuring, and adding
     * a JButton to a container in a single step. It creates a new JButton with the given text,
     * attaches the specified ActionListener to handle button clicks, and adds the button
     * to the provided container.
     * </p>
     *
     * @param container The container to add the button to (such as JFrame, JPanel, etc.)
     * @param text      The text to display on the button
     * @param action    The ActionListener that will handle button click events
     */
    public static void addButtonToFrame(Container container, String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        container.add(button);
    }

    /**
     * Adds a row with a label and value to a panel.
     * <p>
     * This utility method creates a label-value pair and adds it to the provided panel.
     * The label is displayed in bold font on the left side, and the value is displayed
     * on the right side. The row is formatted with a BorderLayout and has padding
     * added above and below.
     * </p>
     * <p>
     * This method is commonly used for displaying detailed information in
     * information panels, forms, or detail views.
     * </p>
     *
     * @param panel The JPanel to which the detail row will be added
     * @param label The text describing the data (will be displayed in bold followed by a colon)
     * @param value The actual data value to be displayed
     */
    public static void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel labelComponent = new JLabel(label + ": ");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel valueComponent = new JLabel(value);

        rowPanel.add(labelComponent, BorderLayout.WEST);
        rowPanel.add(valueComponent, BorderLayout.CENTER);

        panel.add(rowPanel);
    }
}
