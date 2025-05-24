package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected JPanel createForm() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel directorLabel = new JLabel("Director:");
        JTextField directorField = new JTextField();

        JLabel lengthLabel = new JLabel("Length:");
        JTextField lengthField = new JTextField();

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField();

        JButton addButton = new JButton("Add DVD");
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String category = categoryField.getText();
                String director = directorField.getText();
                int length = Integer.parseInt(lengthField.getText());
                float cost = Float.parseFloat(costField.getText());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);
                JOptionPane.showMessageDialog(null, "DVD added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(titleLabel);    panel.add(titleField);
        panel.add(categoryLabel); panel.add(categoryField);
        panel.add(directorLabel); panel.add(directorField);
        panel.add(lengthLabel);   panel.add(lengthField);
        panel.add(costLabel);     panel.add(costField);
        panel.add(new JLabel());  panel.add(addButton);

        return panel;
    }
}

