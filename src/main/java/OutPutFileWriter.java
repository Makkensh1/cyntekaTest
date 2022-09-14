import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class OutPutFileWriter {
    private static Path inputFilePath = Path.of("src/main/resources/output.txt");

    public static void writeFile(List<String> listToWrite) {
        try (FileWriter wr = new FileWriter(String.valueOf(inputFilePath));) {
            for (int i = 0; i < listToWrite.size(); i++) {
                wr.write(listToWrite.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
