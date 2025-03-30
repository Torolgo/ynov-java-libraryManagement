package fr.ynov.librarymanagement.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UiUtils {

    /**
     * Ajoute un bouton à un conteneur (JFrame ou JPanel) avec le texte et l'action spécifiés.
     *
     * @param container Le conteneur auquel ajouter le bouton
     * @param text Le texte à afficher sur le bouton
     * @param action L'action à exécuter lorsque le bouton est cliqué
     * @return Le bouton créé et ajouté
     */
    public static JButton addButtonToFrame(Container container, String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        container.add(button);
        return button;
    }

    /**
     * Crée un panneau de navigation principal avec les boutons pour accéder aux différentes
     * fonctionnalités de l'application.
     *
     * @return Un panneau avec les boutons de navigation
     */
    public static JPanel getJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        addButtonToFrame(panel, "Gérer les Auteurs/Illustrateurs",
                e -> fr.ynov.librarymanagement.gui.person.PersonManager.openPersonWindows());
        addButtonToFrame(panel, "Gérer les Livres",
                e -> fr.ynov.librarymanagement.gui.book.BookManager.openBookWindow());
        addButtonToFrame(panel, "Gérer les Emprunts",
                e -> fr.ynov.librarymanagement.gui.loan.LoanManager.openLoanWindow());

        return panel;
    }

    /**
     * Affiche un message d'erreur dans une boîte de dialogue.
     *
     * @param parent Le composant parent pour la boîte de dialogue
     * @param exception L'exception contenant le message d'erreur à afficher
     */
    public static void showError(Component parent, Exception exception) {
        JOptionPane.showMessageDialog(parent,
                "Erreur: " + exception.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Affiche un message de succès dans une boîte de dialogue.
     *
     * @param parent Le composant parent pour la boîte de dialogue
     * @param message Le message de succès à afficher
     */
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent,
                message,
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Affiche un message de succès et ferme la fenêtre parent.
     *
     * @param frame La fenêtre à fermer après affichage du message
     * @param message Le message de succès à afficher
     */
    public static void showSuccessAndClose(JFrame frame, String message) {
        showSuccess(frame, message);
        frame.dispose();
    }

    /**
     * Ajoute une ligne de détails à un panneau avec une étiquette et une valeur.
     *
     * @param panel Le panneau auquel ajouter la ligne
     * @param label Le texte de l'étiquette
     * @param value La valeur à afficher
     */
    public static void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel labelComponent = new JLabel(label + ":");
        labelComponent.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel valueComponent = new JLabel(value);

        rowPanel.add(labelComponent, BorderLayout.WEST);
        rowPanel.add(valueComponent, BorderLayout.CENTER);

        panel.add(rowPanel);
    }

    /**
     * Crée un champ de texte avec une étiquette et les ajoute à un conteneur.
     *
     * @param container Le conteneur auquel ajouter le champ
     * @param labelText Le texte de l'étiquette
     * @return Le champ de texte créé
     */
    public static JTextField createLabeledTextField(Container container, String labelText) {
        container.add(new JLabel(labelText + ":"));
        JTextField textField = new JTextField();
        container.add(textField);
        return textField;
    }

    /**
     * Crée un composant de liste déroulante avec une étiquette et les ajoute à un conteneur.
     *
     * @param <T> Le type d'items dans la liste
     * @param container Le conteneur auquel ajouter la liste
     * @param labelText Le texte de l'étiquette
     * @param items Les éléments à afficher dans la liste
     * @return Le composant de liste déroulante créé
     */
    public static <T> JComboBox<T> createLabeledComboBox(Container container, String labelText, T[] items) {
        container.add(new JLabel(labelText + ":"));
        JComboBox<T> comboBox = new JComboBox<>(items);
        container.add(comboBox);
        return comboBox;
    }

    /**
     * Crée une zone de texte avec défilement et une étiquette, et les ajoute à un conteneur.
     *
     * @param container Le conteneur auquel ajouter la zone de texte
     * @param labelText Le texte de l'étiquette
     * @param rows Le nombre de lignes
     * @param columns Le nombre de colonnes
     * @return La zone de texte créée
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