import org.jsoup.select.Elements;

import java.io.IOException;

public class DescriptionExtractor {

    public static String getDescription(String word) {
        System.setProperty("console.encoding", "utf-8");
        String result = "";
        String descriptionStart = word.toUpperCase();
        String descriptionEnd = "Русское словесное ударение";
        String url = String.format("http://gramota.ru/slovari/dic/?word=%s&all=x", word);
        try {
            Elements description = JsoupConnector.getDocument(url).select("div.inside.block-content");
            result = description.text();
            result = result.substring(result.indexOf(descriptionStart));
            result = result.substring(0, result.indexOf(descriptionEnd));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toLowerCase();
    }

    public static String getCorrectFormOfWord(String word) {
        System.setProperty("console.encoding", "utf-8");
        String url = "https://www.translate.ru/спряжение%20и%20склонение/русский/" + word;
        String wordStart = "(Кто? Что?) ";
        String delimiter = " ";
        String result = "";
        try {
            Elements wordFormat = JsoupConnector.getDocument(url).select("div.wordFormsSection");
            result = wordFormat.text();
            int index = result.indexOf(wordStart) + wordStart.length();
            result = result.substring(index);
            result = result.substring(0, result.indexOf(delimiter));
        } catch (StringIndexOutOfBoundsException str) {
            throw new NonExistentWordException(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toLowerCase();
    }

}
