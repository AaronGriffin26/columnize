import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(columnize(Files.readAllLines(Paths.get("src/Input1.txt")), 2));
        System.out.println(columnize(Files.readAllLines(Paths.get("src/Input2.txt")), 3));
    }

    public static String columnize(List<String> input, int columns) {
        ArrayList<String> output = new ArrayList<>();
        String[] lines = input.stream().map(String::trim).toArray(String[]::new);
        int maxOutputLineCount = (int)Math.ceil(lines.length / (float)columns);
        int columnWidth = IntStream.range(0, lines.length).reduce(0, (max, ptr) -> Math.max(max, lines[ptr].length()));
        columnWidth = (int)Math.ceil((columnWidth + 1.0) / 4.0) * 4;
        for(int i = 0; i < maxOutputLineCount; i++) {
            output.add("");
        }
        for(int i = 0; i < lines.length; i++) {
            int x = i / maxOutputLineCount;
            int y = (i % maxOutputLineCount);
            String paddedLine = increaseLengthOf(output.get(y), x * columnWidth);
            output.set(y, paddedLine + lines[i]);
        }
        return String.join("\n", output);
    }

    private static String increaseLengthOf(String input, int newLength) {
        StringBuilder output = new StringBuilder(input);
        for(int j = input.length(); j < newLength; j++) {
            output.append(" ");
        }
        return output.toString();
    }
}
