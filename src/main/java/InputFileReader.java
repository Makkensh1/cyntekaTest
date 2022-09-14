import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    static Path inputFilePath = Path.of("src/resources/input.txt");

    public static List<String> getLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> validator(List<String> inputLines){
        List <Integer> emptyLinesIndex = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); i++) {
            String line = inputLines.get(i);
            if(line.length() == 0) {

            }
        }
        return  inputLines;
    }

    public static List<String> getFirstSelection(List<String> inputLines) {
        InputFileReader.getLines(inputFilePath);
        List<String> firstSelection = new ArrayList<>();
        try {
            int index = Integer.valueOf(inputLines.get(0));
            for (int i = 0; i <= index; i++) {
                firstSelection.add(inputLines.get(i));
            }
            return firstSelection;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return firstSelection;
    }

    public static List<String> getSecondSelection(List<String> inputLines) {
        List<String> secondSelection = new ArrayList<>();
        try {
            int count = Integer.valueOf(inputLines.get(0));
            int index = Integer.valueOf(inputLines.get(++count));
            for (int i = ++count; i < inputLines.size(); i++) {
                secondSelection.add(inputLines.get(i));
            }
            return secondSelection;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return secondSelection;
    }

}

