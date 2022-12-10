package main.ui;

import javax.swing.*;

public interface ListActionListener {

    void onAdd(String text);

    void onInsert(String text, int index);

    void onRemove(int index);

    void onSort();

    void onSave(JFileChooser fileChooser, JFrame parentFrame);

    void onLoad(JFileChooser fileChooser, JFrame parentFrame);

    DefaultListModel<Object> getListModel();

    void onSelectType(String type);
}
