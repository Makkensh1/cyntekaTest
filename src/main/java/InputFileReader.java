import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputFileReader {

    private static Path inputFilePath = Path.of("src/main/resources/input.txt");

    private static List<String> lines = getLines(inputFilePath);

    private static List<String> getLines(Path path) {
        try {
            return validate(Files.readAllLines(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> validate(List<String> inputLines) {
        Iterator<String> iterator = inputLines.listIterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.length() == 0 || StringUtils.isBlank(line) || StringUtils.isEmpty(line)) {
                iterator.remove();
            }
        }
        try {
            int count = Integer.valueOf(inputLines.get(0));
            int index = Integer.valueOf(inputLines.get(count + 1));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Number of lines error", e);
        }
        return inputLines;
    }

    public static List<String> getFirstSelection() {
        List<String> firstSelection = new ArrayList<>();
        try {
            int count = Integer.valueOf(lines.get(0));
            for (int i = 1; i <= count; i++) {
                firstSelection.add(lines.get(i));
            }
            return firstSelection;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return firstSelection;
    }

    public static List<String> getSecondSelection() {
        List<String> secondSelection = new ArrayList<>();
        try {
            int index = Integer.valueOf(lines.get(0));
            for (int i = index + 2; i < lines.size(); i++) {
                secondSelection.add(lines.get(i));
            }
            return secondSelection;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return secondSelection;
    }

}

