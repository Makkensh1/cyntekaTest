import java.util.*;

public class WordsMapper {


    public static void compareWordsInLines(Context context) {
        int mainLineNumber = 1;
        Map<Integer, Integer> result = findWordsInLinesAndLinesToRemove(context.getFirstLine(), context.getSecondLineAsWords());
        writeResultAndRemoveMatches(context, result, mainLineNumber);
        mainLineNumber = 2;
        result = findWordsInLinesAndLinesToRemove(context.getSecondLine(), context.getFirstLineAsWords());
        writeResultAndRemoveMatches(context, result, mainLineNumber);
    }

    public static Map<Integer, Integer> findWordsInLinesAndLinesToRemove(List<String> listOfLines, List<List<String>> linesAsWords) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        List<String> foundWords = new ArrayList<>();
        for (int i = 0; i < linesAsWords.size(); i++) {
            for (int j = 0; j < linesAsWords.get(i).size(); j++) {
                String tempFirstList = linesAsWords.get(i).get(j);
                for (int k = 0; k < listOfLines.size(); k++) {
                    if (listOfLines.get(k).contains(tempFirstList)) {
                        resultMap.put(k, i);
                        break;
                    }
                }
            }
        }
        listOfLines.removeAll(foundWords);
        return resultMap;
    }

    public static int findWordMeaningWithLine(String word, List<List<String>> lineAsWords) {
        String wordsMeaning = DescriptionExtractor.getDescription(word);
        int result = -1;
        for (int i = 0; i < lineAsWords.size(); i++) {
            for (int j = 0; j < lineAsWords.get(i).size(); j++) {
                if (wordsMeaning.contains(lineAsWords.get(i).get(j))) {
                    result = i;
                    return result;
                }
            }
        }
        return result;
    }

    public static void compareLinesByMeaning(Context context) {
        int mainLineNumber = 1;
        Map<Integer, Integer> result = getIndexOfMatchedLines(context.getFirstLineAsWords(), context.getSecondLineAsWords());
        writeResultAndRemoveMatches(context, result, mainLineNumber);
        result = getIndexOfMatchedLines(context.getSecondLineAsWords(), context.getFirstLineAsWords());
        mainLineNumber = 2;
        writeResultAndRemoveMatches(context, result, mainLineNumber);
    }

    public static void writeResultAndRemoveMatches(Context context, Map<Integer, Integer> resultMap, int numberOfMainLine) {
        if (!resultMap.isEmpty()) {
            List<String> firstListRemove = new ArrayList<>();
            List<String> secondListRemove = new ArrayList<>();
            List<List<String>> firstLineAsWordsRemove = new ArrayList<>();
            List<List<String>> secondLineAsWordsRemove = new ArrayList<>();
            Set<Map.Entry<Integer, Integer>> resultSet = resultMap.entrySet();
            int firstLineIndex = 0;
            int secondLineIndex = 0;
            for (Map.Entry<Integer, Integer> entry : resultSet) {
                if (numberOfMainLine == 1) {
                    firstLineIndex = entry.getKey();
                    secondLineIndex = entry.getValue();
                } else {
                    firstLineIndex = entry.getValue();
                    secondLineIndex = entry.getKey();
                }
                context.getResult().add(context.getFirstLine().get(firstLineIndex) + ":" + context.getSecondLine().get(secondLineIndex));
                firstListRemove.add(context.getFirstLine().get(firstLineIndex));
                firstLineAsWordsRemove.add(context.getFirstLineAsWords().get(firstLineIndex));
                secondListRemove.add(context.getSecondLine().get(secondLineIndex));
                secondLineAsWordsRemove.add(context.getSecondLineAsWords().get(secondLineIndex));
            }
            context.getFirstLine().removeAll(firstListRemove);
            context.getFirstLineAsWords().removeAll(firstLineAsWordsRemove);
            context.getSecondLine().removeAll(secondListRemove);
            context.getSecondLineAsWords().removeAll(secondLineAsWordsRemove);
        }
    }

    public static Map<Integer, Integer> getIndexOfMatchedLines(List<List<String>> firstLineAsWords, List<List<String>> secondLineAsWords) {
        Map<Integer, Integer> linesToRemove = new HashMap<>();
        for (int i = 0; i < firstLineAsWords.size(); i++) {
            for (int j = 0; j < firstLineAsWords.get(i).size(); j++) {
                int index = findWordMeaningWithLine(firstLineAsWords.get(i).get(j), secondLineAsWords);
                if (index != -1) {
                    linesToRemove.put(i, index);
                }
            }
        }
        return linesToRemove;
    }


    public static void compareLines(Context context) {
        List<String> firstListRemove = new ArrayList<>();
        List<String> secondListRemove = new ArrayList<>();
        for (int i = 0; i < context.getFirstLine().size(); i++) {
            String tempFirstList = context.getFirstLine().get(i).toLowerCase();
            for (int j = 0; j < context.getSecondLine().size(); j++) {
                String tempSecondList = context.getSecondLine().get(j).toLowerCase();
                if (tempFirstList.contains(tempSecondList) || tempSecondList.contains(tempFirstList)) {
                    StringBuilder resultString = new StringBuilder(tempFirstList).append(":").append(tempSecondList);
                    context.getResult().add(resultString.toString());
                    firstListRemove.add(tempFirstList);
                    secondListRemove.add(tempSecondList);
                }
            }
        }
        context.getFirstLine().removeAll(firstListRemove);
        context.getSecondLine().removeAll(secondListRemove);
    }

    public static void collectNotFoundPairs(Context context) {
        if (!context.getFirstLine().isEmpty()) {
            context.getFirstLine().forEach(e -> context.getResult().add(e + ":?"));
        }
        if (!context.getSecondLine().isEmpty()) {
            context.getSecondLine().forEach(e -> context.getResult().add(e + ":?"));
        }
    }
}

