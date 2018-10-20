package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Tests that a {@code Order}'s {@code Date} matches any of the keywords given.
 */
public class OrderDatePredicate implements Predicate<Order> {
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date should be in the format dd-MM-yyyy hh:mm:ss";
    public static final int VALID_SEARCH_DATE_RANGE_SIZE = 2;
    public static final int LOWER_DATE_RANGE_INDEX = 0;
    public static final int UPPER_DATE_RANGE_INDEX = 1;

    private static final SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private Predicate<Order> datePredicate;

    public OrderDatePredicate(List<String> stringDates) {
        List<Date> dates = stringDates.stream().map(stringDate -> parseDate(stringDate)).collect(Collectors.toList());

        if (stringDates.size() == VALID_SEARCH_DATE_RANGE_SIZE) {
            Date lowerDateBoundary = dates.get(LOWER_DATE_RANGE_INDEX);
            Date upperDateBoundary = dates.get(UPPER_DATE_RANGE_INDEX);
            assert (lowerDateBoundary.before(upperDateBoundary) && lowerDateBoundary != upperDateBoundary);
            datePredicate = new OrderDateRangePredicate(lowerDateBoundary, upperDateBoundary);
        } else {
            // take the last date in the list
            int lastIndex = dates.size() - 1;
            datePredicate = new OrderDateSinglePredicate(dates.get(lastIndex));
        }
    }

    @Override
    public boolean test(Order order) {
        return datePredicate.test(order);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderDatePredicate // instanceof handles nulls
                && datePredicate.equals(((OrderDatePredicate) other).datePredicate)); // state check
    }

    /**
     * Parses {@code stringDate} to a Date object
     */
    private Date parseDate(String stringDate) {
        try {
            return sf.parse(stringDate);
        } catch (ParseException pE) {
            checkArgument(false, MESSAGE_DATE_CONSTRAINTS);
            return null;
        }
    }
}
