package main.data.builder;

import main.data.Comparator;

public interface UserTypeBuilder {
    String typeName();

    Object create();

    Comparator getComparator();

    Object createFromString(String s);

    String toString(Object object);
}
