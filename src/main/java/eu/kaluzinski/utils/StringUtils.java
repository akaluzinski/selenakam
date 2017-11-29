package eu.kaluzinski.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class operating on strings
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class StringUtils {

    /**
     * Checks is {@code text} is null or empty.
     *
     * @param text text to be verified
     * @return true is {@code text} is null or empty
     */
    public static boolean isNullOrEmpty(final String text) {
        return text == null || text.isEmpty();
    }

    /**
     * Counts number of list's elements having given sequence.
     *
     * @param list     list of strings
     * @param sequence substring that is counted in {@code list}
     * @return number of {@sequence} occurrences in {@code list}
     * @throws NullPointerException if list or sequence is null
     */
    public static int getNumberOfElementsWithSequence(final List<String> list, final String sequence) {
        if (list == null)
            throw new NullPointerException("List is null");

        if (sequence == null)
            throw new NullPointerException("Sequence is null");

        int i = 0;
        for (String listElement : list) {
            if (listElement == null)
                continue;
            else if (listElement.contains(sequence))
                i++;
        }
        return i;
    }

    /**
     * Extracts the first integer number found in the string
     *
     * @param text containing or not integer number
     * @return the first number or null if not found
     * @throws NullPointerException when text is null
     */
    public static Integer extractFirstIntegerFromString(final String text) {

        Matcher m = Pattern.compile("-?\\d+").matcher(text);
        if (!m.find()) {
            return null;
        }
        return Integer.parseInt(m.group());
    }
}
