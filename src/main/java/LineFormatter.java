import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LineFormatter {
    static final int LENGTH_WITHOUT_PREPOSITION = 2;

    public static List<String> splitLine(String inputLine) {
        List<String> splintedLines = new ArrayList<>();
        splintedLines.addAll(List.of((inputLine.split("\\s"))));
        return splintedLines;
    }

    public static List<List<String>> getLineByWords(List<String> list) {
        List<List<String>> result = new ArrayList<>();
        result.addAll(list.stream().map(e -> removePrepositionFromLine(splitLine(e.toLowerCase()))).collect(Collectors.toList()));
        return result;
    }

    public static List<List<String>> getLineByWordsInFirstForm(List<List<String>> listOfLines) {
        List<List<String>> formattedList = new ArrayList<>();
        for (int i = 0; i < listOfLines.size(); i++) {
            List<String> firstFormWords = new ArrayList<>();
            for (int j = 0; j < listOfLines.get(i).size(); j++) {
                String temp = DescriptionExtractor.getCorrectFormOfWord(listOfLines.get(i).get(j));
                firstFormWords.add(temp);
            }
            formattedList.add(firstFormWords);
        }
        return formattedList;
    }

    public static List<String> removePrepositionFromLine(List<String> inputList) {
        if (inputList.size() > LENGTH_WITHOUT_PREPOSITION) {
            String preposition = inputList.stream().min(Comparator.comparing((e -> e.length()))).get();
            inputList.remove(preposition);
        }
        return inputList;
    }
}
