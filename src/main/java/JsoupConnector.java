import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupConnector {
    public static Document getDocument(String stringURL) throws IOException {
        return Jsoup
                .connect(stringURL)
                .userAgent("Chrome/103.0.0.0 Safari/537.36")
                .referrer("https://www.google.com/")
                .get();

    }
}
