package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField();

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            int id = Integer.parseInt(titleField.getText());
            String title = titleField.getText();
            String category = categoryField.getText();
            float cost = Float.parseFloat(costField.getText());

            Book book = new Book(id,title, category, cost);
            store.addMedia(book);
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        });

        panel.add(idLabel);       panel.add(idField);
        panel.add(titleLabel);    panel.add(titleField);
        panel.add(categoryLabel);  panel.add(categoryField);
        panel.add(costLabel);       panel.add(costField);
        panel.add(new JLabel());
        panel.add(addButton);

        return panel;
    }
}
