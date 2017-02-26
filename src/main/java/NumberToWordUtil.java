import com.ibm.icu.text.RuleBasedNumberFormat;
import com.sun.javafx.beans.annotations.NonNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Igor on 19.02.2017.
 */
public class NumberToWordUtil {

    public static List<String> numbersToWords(@NonNull List<BigInteger> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Numbers must not be null or empty");
        }
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        List<String> words = new ArrayList<>();
        for (BigInteger number : numbers) {
            words.add(nf.format(number));
        }
        return words;
    }
}
