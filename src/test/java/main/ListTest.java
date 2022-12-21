package main;

import junit.framework.Assert;
import main.data.IList;
import main.data.List;
import main.data.PolarVector;
import main.data.UserFactory;
import main.data.builder.UserTypeBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ListTest extends Assert {

    private IList actualList, expectedList;
    private UserFactory userFactory;
    private UserTypeBuilder userTypeBuilder;

    @Before
    public void setUp() throws Exception {
        userFactory = new UserFactory();
        userTypeBuilder = userFactory.getBuilder("Double");
        actualList = new List();
        expectedList = new List();
    }

    @Test
    public void test1() {
        System.out.println("TEST 1. Increasing order");
        for (int i = 0; i <= 10; i++) {
            actualList.add(userTypeBuilder.createFromString(i+""));
            expectedList.add(userTypeBuilder.createFromString(i+""));

        }
        actualList.sort(userTypeBuilder.getComparator());

        assertEquals(expectedList,actualList);
    }

    @Test
    public void test2() {
        System.out.println("TEST 1. Decreasing order");
        for (int i = 10; i >= 0; i--) {
            actualList.add(userTypeBuilder.createFromString(i+""));
        }
        for (int i = 0; i <= 10; i++) {
            expectedList.add(userTypeBuilder.createFromString(i+""));
        }
        actualList.sort(userTypeBuilder.getComparator());

        assertEquals(expectedList,actualList);
    }

    @Test
    public void test3() {
        System.out.println("TEST 3. All values are equal");
        for (int i = 0; i <= 10; i++) {
            actualList.add(userTypeBuilder.createFromString(10+""));
            expectedList.add(userTypeBuilder.createFromString(10+""));
        }
        actualList.sort(userTypeBuilder.getComparator());

        assertEquals(expectedList,actualList);
    }

    @Test
    public void test4() {
        System.out.println("TEST 4. Values isn't ordered");
        var randNums = new ArrayList<>(Arrays.asList("1","2","5","7","5","2","4","4","3","1","1"));
        for (String num: randNums) {
            actualList.add(userTypeBuilder.createFromString(num));
        }

        Collections.sort(randNums);
        for (String num: randNums) {
            expectedList.add(userTypeBuilder.createFromString(num));
        }
        actualList.sort(userTypeBuilder.getComparator());

        assertEquals(expectedList,actualList);
    }

    @Test
    public void testPerf() {
        for (int i = 64; i < 40000; i *= 2) {
            int n = i * 10000;
            System.out.print(n+"\t");
            List list = new List();
            for (int j = 0; j < n; j++) list.add(userTypeBuilder.create());

            long start = System.nanoTime();

            try {
                list.sort(userTypeBuilder.getComparator());
            } catch (StackOverflowError ignored) {
                System.err.println("Stack error");
                return;
            }
            long end = System.nanoTime();
            System.out.println((end - start) * 1.0 / 1_000_000_000);
        }
    }
}
