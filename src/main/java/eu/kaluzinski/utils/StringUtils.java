package eu.kaluzinski.utils;

import java.util.List;

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
     * @param list
     * @param sequence
     * @return
     */
    public static int getNumberOfElementsWithSequence(final List<String> list, final String sequence) {

        if (list == null) {
            throw new NullPointerException("List is null");
        }
        int i = 0;
        for (String listElement : list) {
            if (listElement == null)
                continue;
            else if (listElement.contains(sequence))
                i++;
        }
        return i;
    }
}
