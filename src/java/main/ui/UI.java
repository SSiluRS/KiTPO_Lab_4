package main.ui;

import main.data.UserFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UI extends JFrame {
    private final JList<Object> jList;

    public UI(ListActionListener actionListener) throws HeadlessException {
        this.jList = new JList<>(actionListener.getListModel());
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel listPanel = new JPanel();
        listPanel.add(new JScrollPane(jList));
        container.add(listPanel, BorderLayout.CENTER);

        JPanel bottomJPanel = new JPanel();
        container.add(bottomJPanel, BorderLayout.PAGE_END);

        JFileChooser fileChooser = new JFileChooser();
        JFrame parentFrame = new JFrame();

        bottomJPanel.setLayout(new BoxLayout(bottomJPanel, BoxLayout.Y_AXIS));
        JPanel chooseType = new JPanel();
        bottomJPanel.add(chooseType);
        JComboBox<String> comboBox = new JComboBox<>(UserFactory.getAllTypes().toArray(new String[0]));
        chooseType.add(comboBox);
        comboBox.addActionListener(e -> {
            JComboBox source = (JComboBox) e.getSource();
            String selectedItem = (String) source.getSelectedItem();
            actionListener.onSelectType(selectedItem);
            bottomJPanel.add(new UIAction(actionListener::onAdd, "Добавить элемент"));
            bottomJPanel.add(new UIAction((text) -> actionListener.onInsert(text, jList.getSelectedIndex()), "Вставить элемент перед выбранным"));
            bottomJPanel.add(new UIAction(() -> actionListener.onRemove(jList.getSelectedIndex()), "Удалить выбранный элемент"));
            bottomJPanel.add(new UIAction(actionListener::onSort, "Сортировка"));
            bottomJPanel.add(new UIAction(() -> actionListener.onSave(fileChooser, parentFrame), "Сохранить в бинарный файл"));
            bottomJPanel.add(new UIAction(() -> actionListener.onLoad(fileChooser, parentFrame), "Загрузить из бинарного файла"));
            bottomJPanel.remove(chooseType);
            revalidate();
            repaint();
        });
        setTitle("Lab1");
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
