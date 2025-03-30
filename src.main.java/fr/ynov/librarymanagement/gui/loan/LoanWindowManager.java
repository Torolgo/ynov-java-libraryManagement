package fr.ynov.librarymanagement.gui.loan;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;

import static fr.ynov.librarymanagement.factory.Reader.loadAllBooks;
import static fr.ynov.librarymanagement.gui.UiUtils.*;

public class LoanWindowManager {

    static void openTakeBookWindow() {
        loadAllBooks();

        JFrame takeBookFrame = new JFrame("Emprunter un Livre");
        takeBookFrame.setSize(400, 200);
        takeBookFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField idField = createLabeledTextField(takeBookFrame, "ID du Livre");

        addButtonToFrame(takeBookFrame, "Emprunter", e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                LoanBookProcessor.processBookAction(id, true, takeBookFrame);
            } catch (Exception ex) {
                showError(takeBookFrame, ex);
            }
        });

        takeBookFrame.setVisible(true);
    }

    static void openReturnBookWindow() {
        loadAllBooks();

        JFrame returnBookFrame = new JFrame("Retourner un Livre");
        returnBookFrame.setSize(400, 200);
        returnBookFrame.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField idField = createLabeledTextField(returnBookFrame, "ID du Livre");

        addButtonToFrame(returnBookFrame, "Retourner", e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                LoanBookProcessor.processBookAction(id, false, returnBookFrame);
            } catch (Exception ex) {
                showError(returnBookFrame, ex);
            }
        });

        returnBookFrame.setVisible(true);
    }
}