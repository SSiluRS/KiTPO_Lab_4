package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class UIAction extends JPanel {

    public UIAction(EmptyConsumer action, String buttonText) {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> action.accept());
        add(button);
    }

    public UIAction(Consumer<String> action, String buttonText) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 25));
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> {
            action.accept(textField.getText());
            textField.setText("");
        });
        add(button);
        add(textField);
    }
}
