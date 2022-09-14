import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Context {
    private List<String> firstLine;
    private List<String> secondLine;
    private List<List<String>> firstLineAsWords;
    private List<List<String>> secondLineAsWords;
    private List<String> result = new ArrayList<>();


}
