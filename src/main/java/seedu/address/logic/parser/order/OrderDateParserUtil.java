package seedu.address.logic.parser.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Parses of date keywords and returns a valid date list
 */
public class OrderDateParserUtil {
    private static final int VALID_DATE_LIST_SIZE = 2;

    /**
     * Sorts the dates in ascending order
     * Returns a list of size 2 if more than 2 date fields are specified
     */
    public List<String> parseDateKeywords(List<String> keywords) {
        final int firstIndex = 0;
        final int lastIndex = keywords.size() - 1;

        Collections.sort(keywords);

        if (keywords.size() > VALID_DATE_LIST_SIZE) {
            List<String> newKeywords = new ArrayList<>();
            newKeywords.add(keywords.get(firstIndex));
            newKeywords.add(keywords.get(lastIndex));
            return newKeywords;
        }

        return keywords;
    }
}
