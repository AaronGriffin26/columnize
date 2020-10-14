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
        ArrayList<String> input2 = new ArrayList<>();
        input2.add("One");
        input2.add("Two");
        input2.add("Three");
        input2.add("Four");
        input2.add("Five");
        input2.add("Six");
        input2.add("Seven");
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
        assertAll(() -> assertEquals("One     Five\nTwo     Six\nThree   Seven\nFour", Main.columnize(input1, 2)), () -> assertEquals("One             Ten             Nineteen\nTwo             Eleven          Twenty\nThree           Twelve          Twenty One\nFour            Thirteen        Twenty Two\nFive            Fourteen        Twenty Three\nSix             Fifteen         Twenty Four\nSeven           Sixteen         Twenty Five\nEight           Seventeen\nNine            Eighteen", Main.columnize(input2, 3)));
    }

    @Test
    void increaseLengthOf() {
        assertEquals("pad     " + "|", Main.increaseLengthOf("pad", 8) + "|");
        assertEquals("pad        " + "|", Main.increaseLengthOf("pad", 11) + "|");
    }
}