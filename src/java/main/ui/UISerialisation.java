package main.ui;

import main.data.IList;
import main.data.builder.UserTypeBuilder;

import java.io.*;

public class UISerialisation {

    public static  void saveToFile(String filename, IList list, UserTypeBuilder builder) throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(builder.typeName()+"\n");
            list.forEach(el -> {
                try {
                    writer.write(builder.toString(el)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  IList loadFromFile(String filename, UserTypeBuilder builder, IList list) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            line = br.readLine();
            if (!builder.typeName().equals(line)) {
                throw new Exception("Wrong file structure");
            }

            while ((line = br.readLine()) != null) {
                list.add(builder.createFromString(line));
            }
            return list;
        }
    }
}
