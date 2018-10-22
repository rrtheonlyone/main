package seedu.address.logic.parser.order;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.logic.commands.order.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderAddressContainsKeywordPredicate;
import seedu.address.model.order.OrderDatePredicate;
import seedu.address.model.order.OrderFoodContainsKeywordPredicate;
import seedu.address.model.order.OrderNameContainsKeywordPredicate;
import seedu.address.model.order.OrderPhoneContainsKeywordPredicate;
/**
 * Util to parse order's predicate
 */
public class OrderPredicateUtil {
    private static final String STRING_PREFIX_NAME = "n/";
    private static final String STRING_PREFIX_PHONE = "p/";
    private static final String STRING_PREFIX_ADDRESS = "a/";
    private static final String STRING_PREFIX_DATE = "dt/";
    private static final String STRING_PREFIX_FOOD = "f/";

    private static final String MESSAGE_EMPTY_KEYWORD = "%1$s cannot be empty";

    private Predicate<Order> chainedPredicated;

    /**
     * Parses the given {@code argMultimap} to a chained predicate
     * and returns the chained predicate
     * @throws ParseException if any supplied prefix is empty
     */
    public Predicate<Order> parsePredicate(ArgumentMultimap argMultimap) throws ParseException {
        Set<Prefix> prefixes = argMultimap.getAllPrefixes();

        for (Prefix prefix : prefixes) {
            if (prefix.toString().equals("")) {
                continue;
            }

            List<String> keywords = argMultimap.getAllValues(prefix);

            chainPredicate(prefix, keywords);
        }

        return chainedPredicated;
    }

    /**
     * Parses the different {@code prefix}, forms the related predicate and chains them up
     * @throws ParseException if a invalid {@code prefix} is supplied
     */
    private void chainPredicate(Prefix prefix, List<String> keywords) throws ParseException {
        switch (prefix.toString()) {
        case STRING_PREFIX_NAME:
            String name = getLastValueFromList(keywords);
            String[] nameKeywords = trimAndSplitStringByWhiteSpaces(name);
            OrderNameContainsKeywordPredicate namePredicate =
                    new OrderNameContainsKeywordPredicate(Arrays.asList(nameKeywords));

            setToPredicate(namePredicate);

            break;

        case STRING_PREFIX_PHONE:
            String phone = getLastValueFromList(keywords);
            String[] phoneKeywords = trimAndSplitStringByWhiteSpaces(phone);
            OrderPhoneContainsKeywordPredicate phonePredicate =
                    new OrderPhoneContainsKeywordPredicate(Arrays.asList(phoneKeywords));

            setToPredicate(phonePredicate);

            break;

        case STRING_PREFIX_ADDRESS:
            String address = getLastValueFromList(keywords).trim();
            OrderAddressContainsKeywordPredicate addressPredicate = new OrderAddressContainsKeywordPredicate(address);

            setToPredicate(addressPredicate);

            break;

        case STRING_PREFIX_DATE:
            List<Date> date = new OrderDatePredicateUtil().parseDateKeywords(keywords);
            OrderDatePredicate datePredicate = new OrderDatePredicate(date);

            setToPredicate(datePredicate);

            break;

        case STRING_PREFIX_FOOD:
            String food = getLastValueFromList(keywords);
            String[] foodKeywords = trimAndSplitStringByWhiteSpaces(food);
            OrderFoodContainsKeywordPredicate foodPredicate =
                    new OrderFoodContainsKeywordPredicate(Arrays.asList(foodKeywords));

            setToPredicate(foodPredicate);

            break;

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private String getLastValueFromList(List<String> list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    private String[] trimAndSplitStringByWhiteSpaces(String value) {
        return value.trim().split("\\s+");
    }

    /**
     * Set {@code chainedPredicate} to {@code predicate} if predicate is not set
     * else AND the predicates
     */
    private void setToPredicate(Predicate<Order> predicate) {
        // predicate is not set
        if (chainedPredicated == null) {
            chainedPredicated = predicate;
        } else {
            chainedPredicated = chainedPredicated.and(predicate);
        }
    }
}
