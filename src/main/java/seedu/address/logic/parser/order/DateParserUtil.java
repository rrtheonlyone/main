package seedu.address.logic.parser.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Parser for optional hh:mm:ss
 */
public class DateParserUtil {
    private static final String DATE_WITH_TIME_PATTERN = "dd-MM-yyyy hh:mm:ss";
    private static final String DATE_ONLY_PATTERN = "dd-MM-yyyy";
    private static final SimpleDateFormat DATE_WITH_TIME = new SimpleDateFormat(DATE_WITH_TIME_PATTERN);
    private static final SimpleDateFormat DATE_ONLY = new SimpleDateFormat(DATE_ONLY_PATTERN);

    /**
     * Parses Date based on {@ocde stringDate} to DATE_WITH_TIME or DATE_ONLY
     */
    public static Date parse(String stringDate) throws ParseException {
        boolean isDateWithTime = stringDate.length() > DATE_ONLY_PATTERN.length();

        try {
            if (isDateWithTime) {
                return DATE_WITH_TIME.parse(stringDate);
            } else {
                return DATE_ONLY.parse(stringDate);
            }
        } catch (ParseException pE) {
            throw pE;
        }
    }
}
