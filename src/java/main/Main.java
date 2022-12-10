package main;

import main.data.List;
import main.data.UserFactory;
import main.data.builder.UserTypeBuilder;
import main.ui.ListActionListener;
import main.ui.ListActionListenerImpl;
import main.ui.UI;
import main.ui.UISerialisation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedMap;

public class Main {

    private static void test(UserTypeBuilder builder) {
        for (int i = 1; i < 2000; i *= 2) {
            int n = i * 1000;
            System.out.println("N = " + n);
            List list = new List();
            for (int j = 0; j < n; j++) list.add(builder.create());

            long start = System.nanoTime();

            try {
                list.sort(builder.getComparator());
            } catch (StackOverflowError ignored) {
                System.err.println("Stack error");
                return;
            }
            long end = System.nanoTime();
            System.out.println("Millis elapsed " + (end - start) * 1.0 / 1_000_000);
        }
    }

    public static void main(String[] args) {
        var userTypeList = Arrays.asList("Double");

        ListActionListener listActionListener = new ListActionListenerImpl();
        //new UI(listActionListener);

       /* for (String s : userTypeList) {

            System.out.println(s + ": ");

            UserTypeBuilder builder = UserFactory.getBuilder(s);

            test(builder);

            List list = new List();
            for (int j = 0; j < 10; j++) list.add(builder.createFromString(String.valueOf(j)));
            System.out.println("initial");
            list.forEach(System.out::println);

            list = new List();
            for (int j = 10; j >= 0; j--) list.add(builder.createFromString(String.valueOf(j)));
            System.out.println("initial");
            list.forEach(System.out::println);

            list.sort(builder.getComparator());
            System.out.println("\nafter sort");
            list.forEach(System.out::println);

            try {
                UISerialisation.saveToFile("file.dat",list,builder);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            List list1 = new List();
            try {
                UISerialisation.loadFromFile("file.dat",builder, list1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("\nSaved List:");
            list.forEach(System.out::println);
            System.out.println("\n\n");
            System.out.println("Loaded List:");
            list1.forEach(System.out::println);

        }*/

    }
}
