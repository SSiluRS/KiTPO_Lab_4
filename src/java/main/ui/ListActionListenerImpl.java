package main.ui;

import main.data.IList;
import main.data.List;
import main.data.UserFactory;
import main.data.builder.UserTypeBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ListActionListenerImpl implements ListActionListener {


    protected IList items = new List();

    protected final String filename = "file.dat";

    protected final DefaultListModel<Object> listModel = new DefaultListModel<>();
    protected UserTypeBuilder builder;

    @Override
    public DefaultListModel<Object> getListModel() {
        return listModel;
    }

    @Override
    public void onSelectType(String type) {
        try {
            builder = UserFactory.getBuilder(type);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void onAdd(String text) {
        if (text.isEmpty()) return;
        Object data = builder.createFromString(text);
        items.add(data);
        listModel.addElement(data);
    }

    @Override
    public void onInsert(String text, int index) {
        if (text.isEmpty()) return;
        Object data = builder.createFromString(text);
        items.add(data, index);
        listModel.add(index, data);
    }

    @Override
    public void onRemove(int index) {
        items.remove(index);
        listModel.remove(index);
    }

    @Override
    public void onSort() {
        items.sort(builder.getComparator());
        listModel.clear();
        items.forEach(listModel::addElement);
    }

    @Override
    public void onSave(JFileChooser fileChooser, JFrame parentFrame) {
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try {
                UISerialisation.saveToFile(fileToSave.getAbsolutePath(), items, builder);
            } catch (FileNotFoundException e) {
                System.err.println("Unable to write list to a file");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoad(JFileChooser fileChooser, JFrame parentFrame) {
        int userSelection = fileChooser.showOpenDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                items = UISerialisation.loadFromFile(fileToSave.getAbsolutePath(), builder, new List());
                listModel.clear();
                items.forEach(listModel::addElement);
            } catch (Exception e) {
                System.err.println("Unable to read list from a file");
                e.printStackTrace();
            }
        }
    }
}
