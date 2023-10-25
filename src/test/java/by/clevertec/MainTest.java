package by.clevertec;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void task4Test() throws NoSuchMethodException {

        final ByteArrayOutputStream actual = setOut();
        final Method task4 = Main.class.getDeclaredMethod("task4");
        task4.setAccessible(true);
        try {
            task4.invoke(new Object());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(out);
        }
        assertTrue(actual.toString().contains("Number of all sex animals Female: 476"));
    }

    @Test
    void task15() throws NoSuchMethodException {

        final ByteArrayOutputStream actual = setOut();
        final Method task15 = Main.class.getDeclaredMethod("task15");
        task15.setAccessible(true);
        try {
            task15.invoke(new Object());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(out);
        }
        assertTrue(actual.toString().contains("The total cost of maintenance of all plants: 315362,05"));
    }

    private ByteArrayOutputStream setOut() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        return byteArrayOutputStream;
    }
}