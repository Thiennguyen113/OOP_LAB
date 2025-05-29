package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField();

        JLabel authorsLabel = new JLabel("Authors (comma-separated):");
        JTextField authorsField = new JTextField();

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = 0f;
            try {
                cost = Float.parseFloat(costField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid cost value.");
                return;
            }
            String authorsText = authorsField.getText();
            List<String> authors = new ArrayList<>();
            if (!authorsText.trim().isEmpty()) {
                authors = Arrays.asList(authorsText.split("\\s*,\\s*"));
            }

            try {
                Book book = new Book(title, category, cost, authors);
                store.addMedia(book);
                JOptionPane.showMessageDialog(null, "Book added successfully!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Failed to add book: " + ex.getMessage());
            }
        });

        panel.add(titleLabel);    panel.add(titleField);
        panel.add(categoryLabel); panel.add(categoryField);
        panel.add(costLabel);     panel.add(costField);
        panel.add(authorsLabel);  panel.add(authorsField);
        panel.add(new JLabel());
        panel.add(addButton);

        return panel;
    }
}