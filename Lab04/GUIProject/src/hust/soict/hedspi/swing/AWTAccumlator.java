package hust.soict.hedspi.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AWTAccumlator extends JFrame {
    private JTextField tfInput;    // Accumulated sum, init to 0
    private int sum = 0;
    private JTextField tfSum;

    // Constructor to setup the GUI components and event handlers
    public AWTAccumlator() {
        setLayout(new FlowLayout());

        add(new JLabel("Enter an Integer "));
        tfInput = new JTextField(10);
        add(tfInput);
        tfInput.addActionListener(new TFInputListener());
        add(new JLabel("The Accumulated Sum is:"));

        tfSum = new JTextField(10);
        tfSum.setEditable(false);
        add(tfSum);

        setTitle("Swing Accumulator");
        setSize(350, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTAccumlator();
    }

    private class TFInputListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int number = Integer.parseInt(tfInput.getText());
            sum += number;
            tfInput.setText("");
            tfSum.setText(sum + "");
        }
    }
}