import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Igor on 19.02.2017.
 */
public class Main {

    public static final String input = System.getProperty("user.dir") + "/input.txt";
    public static final String output = System.getProperty("user.dir") + "/output.txt";

    public static void main(String[] args) {
        FileManager fileManager = FileManager.getInstance();
        try {
            List<String> words = NumberToWordUtil.numbersToWords(fileManager.readNumbers(input));
            fileManager.writeWords(output, words);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
