import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        switch(args.length) {
            case 0:
                args = new String[2];
                break;
            case 1:
                String[] newArgs = new String[2];
                newArgs[0] = args[0];
                args = newArgs;
                break;
        }
        boolean exit = false;
        if(args[0] != null) {
            try {
                if(args[0].equals("")) {
                    exit = true;
                } else if(!Files.exists(Paths.get(args[0]))) {
                    System.out.println("File \"" + args[0] + "\" does not exist.");
                    exit = true;
                }
            } catch(InvalidPathException e) {
                System.out.println("Please enter a valid path. " + e.getMessage());
                exit = true;
            }
        }
        if(args[1] != null) {
            try {
                int i = Integer.parseInt(args[1]);
                if(i < 2) {
                    System.out.println("Cannot columnize with less than 2 columns.");
                    exit = true;
                }
            } catch(NumberFormatException ignored) {
                System.out.println("Column count \"" + args[1] + "\" is not a number.");
                exit = true;
            }
        }
        if(exit) {
            System.out.println("Args: <filepath> <columns>");
            return;
        }

        if(args[0] == null || args[0].equals("")) {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("Enter path to input: ");
                args[0] = scanner.nextLine();
                try {
                    if(args[0].equals(""))
                        System.out.println("Please enter a path to a text file.");
                    else if(!Files.exists(Paths.get(args[0])))
                        System.out.println("File \"" + args[0] + "\" does not exist.");
                    else
                        break;
                } catch(InvalidPathException e) {
                    System.out.println("Please enter a valid path. " + e.getMessage());
                }
            }
        }
        if(args[1] == null || args[1].equals("")) {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("Enter number of columns: ");
                args[1] = scanner.nextLine();
                try {
                    int i = Integer.parseInt(args[1]);
                    if(i < 2)
                        System.out.println("Cannot columnize with less than 2 columns.");
                    else
                        break;
                } catch(NumberFormatException e) {
                    System.out.println("Please enter a number.");
                }
            }
        }
        System.out.println(columnize(Files.readAllLines(Paths.get(args[0])), Integer.parseInt(args[1])));
    }

    public static String columnize(List<String> input, int columns) {
        String[] trimmedLines = input.stream().map(String::trim).toArray(String[]::new);
        int outputLineCount = (int)Math.ceil(trimmedLines.length / (float)columns);
        int columnWidth = getColumnWidth(trimmedLines);
        List<String> output = preallocatedStringList(outputLineCount);
        for(int i = 0; i < trimmedLines.length; i++) {
            int x = i / outputLineCount;
            int y = (i % outputLineCount);
            String paddedLine = increaseLengthOf(output.get(y), x * columnWidth);
            output.set(y, paddedLine + trimmedLines[i]);
        }
        return String.join("\n", output);
    }

    public static ArrayList<String> preallocatedStringList(int outputLineCount) {
        return IntStream.range(0, outputLineCount).mapToObj(i -> "").collect(Collectors.toCollection(ArrayList::new));
    }

    public static int getColumnWidth(String[] trimmedLines) {
        int columnWidth = IntStream.range(0, trimmedLines.length).reduce(0, (max, ptr) -> Math.max(max, trimmedLines[ptr].length()));
        columnWidth = (int)Math.ceil((columnWidth + 1.0) / 4.0) * 4;
        return columnWidth;
    }

    public static String increaseLengthOf(String input, int newLength) {
        if(newLength <= input.length())
            return input;
        return input + String.format("%" + (newLength - input.length()) + "s", "");
    }
}
