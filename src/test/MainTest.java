import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void main() throws IOException {
        String[] args = new String[2];
        args[0] = "src/test/test.txt";
        args[1] = "2";
        Main.main(args);
    }

    @Test
    void columnize() {
        ArrayList<String> input1 = new ArrayList<>();
        input1.add("One");
        input1.add("Two");
        input1.add("Three");
        input1.add("Four");
        input1.add("Five");
        input1.add("Six");
        input1.add("Seven");
        ArrayList<String> input2 = new ArrayList<>(input1);
        input2.add("Eight");
        input2.add("Nine");
        input2.add("Ten");
        input2.add("Eleven");
        input2.add("Twelve");
        input2.add("Thirteen");
        input2.add("Fourteen");
        input2.add("Fifteen");
        input2.add("Sixteen");
        input2.add("Seventeen");
        input2.add("Eighteen");
        input2.add("Nineteen");
        input2.add("Twenty");
        input2.add("Twenty One");
        input2.add("Twenty Two");
        input2.add("Twenty Three");
        input2.add("Twenty Four");
        input2.add("Twenty Five");
        assertAll(
                () -> assertEquals("One     Five\n" +
                        "Two     Six\n" +
                        "Three   Seven\n" +
                        "Four", Main.columnize(input1, 2)),
                () -> assertEquals("One             Ten             Nineteen\n" +
                        "Two             Eleven          Twenty\n" +
                        "Three           Twelve          Twenty One\n" +
                        "Four            Thirteen        Twenty Two\n" +
                        "Five            Fourteen        Twenty Three\n" +
                        "Six             Fifteen         Twenty Four\n" +
                        "Seven           Sixteen         Twenty Five\n" +
                        "Eight           Seventeen\n" +
                        "Nine            Eighteen", Main.columnize(input2, 3)));
    }

    @Test
    void getColumnWidth() {
        ArrayList<String> input1 = new ArrayList<>();
        input1.add("Four");
        input1.add("Six---");
        input1.add("2-");
        ArrayList<String> input2 = new ArrayList<>(input1);
        input2.add("Eleven-----");
        input2.add("Thirty------------------------");
        assertEquals(8, Main.getColumnWidth(input1.toArray(new String[0])));
        assertEquals(32, Main.getColumnWidth(input2.toArray(new String[0])));
    }

    @Test
    void increaseLengthOf() {
        assertEquals("pad|", Main.increaseLengthOf("pad", 0) + "|");
        assertEquals("pad|", Main.increaseLengthOf("pad", 3) + "|");
        assertEquals("pad     |", Main.increaseLengthOf("pad", 8) + "|");
        assertEquals("pad        |", Main.increaseLengthOf("pad", 11) + "|");
    }
}