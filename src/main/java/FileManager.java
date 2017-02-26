import com.sun.javafx.beans.annotations.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 19.02.2017.
 */
public class FileManager {

    private static FileManager instance = null;
    private static Charset encoding = null;

    public static FileManager getInstance() {
        return instance == null ? new FileManager() : instance;
    }

    public static FileManager getInstance(@NonNull Charset encoding) {
        return instance == null ? new FileManager(encoding) : instance;
    }

    private FileManager() {
        encoding = StandardCharsets.UTF_8;
    }

    private FileManager(@NonNull Charset encoding) {
        FileManager.encoding = encoding;
    }

    @NonNull
    public List<String> readStrings(@NonNull String path) throws URISyntaxException, IOException {
        return Files.readAllLines(findFile(path), encoding);
    }

    @NonNull
    public List<BigInteger> readNumbers(@NonNull String path) throws IOException, URISyntaxException {
        return stringsToNumbers(readStrings(path));
    }

    @NonNull
    public List<BigInteger> stringsToNumbers(@NonNull List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            throw new IllegalArgumentException("Strings must not be null or empty");
        }
        final List<BigInteger> numbers = new ArrayList<>();
        for (final String string : strings) {
            if (StringUtils.isNumeric(string)) {
                numbers.add(new BigInteger(string));
            }
        }
        return numbers;
    }

    @NonNull
    public List<String> numbersToString(List<BigInteger> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Numbers must not be null or empty");
        }
        final List<String> strings = new ArrayList<>();
        for (final BigInteger number : numbers) {
            strings.add(number.toString());
        }
        return strings;
    }

    public Path writeWords(@NonNull String path, @NonNull List<String> words) throws URISyntaxException, IOException {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("numbers must not be null or empty");
        }
        return Files.write(findFile(path), words, encoding, StandardOpenOption.WRITE);
    }

    @NonNull
    protected Path findFile(@NonNull String path) throws URISyntaxException {
        if (StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Path must not be null or empty");
        }
        return Paths.get(path);
    }
}
