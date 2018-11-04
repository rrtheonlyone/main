package seedu.address.logic.parser.order;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNameContainsKeywordPredicate;

public class OrderPredicateUtilTest {
    @Test
    public void test_emptyTags_throwsParseException() throws ParseException {
        assertParseFailure(" n/", OrderPredicateUtil.MESSAGE_EMPTY_KEYWORD);
        assertParseFailure(" p/", OrderPredicateUtil.MESSAGE_EMPTY_KEYWORD);
        assertParseFailure(" a/", OrderPredicateUtil.MESSAGE_EMPTY_KEYWORD);
        assertParseFailure(" dt/", OrderPredicateUtil.MESSAGE_EMPTY_KEYWORD);
        assertParseFailure(" f/", OrderPredicateUtil.MESSAGE_EMPTY_KEYWORD);
    }

    @Test
    public void test_singleValidPredicate_returnsTrue() throws ParseException {
        List<String> name = Collections.singletonList("alex");
        Predicate<Order> expectedPredicate = new OrderNameContainsKeywordPredicate(name);

<<<<<<< d49219fb5e138b1a75d473643c000c31596d51d2
        ArgumentMultimap argMultimap = tokenizeInput(" n/alex");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(argMultimap);
=======
        ArgumentMultimap emptyAddress = tokenizeInput(" a/");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyAddress);
    }

    @Test
    public void test_emptyDateTag_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);

        ArgumentMultimap emptyDate = tokenizeInput(" dt/");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyDate);
    }

    @Test
    public void test_emptyFoodTag_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);

        ArgumentMultimap emptyFood = tokenizeInput(" f/");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyFood);
    }

    @Test
    public void test_emptyStatusTag_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);

        ArgumentMultimap emptyStatus = tokenizeInput(" st/");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyStatus);
    }

    @Test
    public void test_singlePredicate() throws ParseException {
        Predicate<Order> expectedPredicate;

        ArgumentMultimap emptyName = tokenizeInput(" n/alex");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyName);

        List<String> names = Collections.singletonList("alex");
        expectedPredicate = new OrderNameContainsKeywordPredicate(names);
>>>>>>> Add and update tests for find order by status

        assertEquals(predicate, expectedPredicate);
    }

    private ArgumentMultimap tokenizeInput(String input) {
        return ArgumentTokenizer.tokenize(
                input, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_DATE, PREFIX_FOOD, PREFIX_STATUS);
    }

    /**
     * Asserts that the parsing of {@code userInput} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    private void assertParseFailure(String userInput, String expectedMessage) {
        try {
            ArgumentMultimap emptyField = tokenizeInput(userInput);
            Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyField);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(String.format(expectedMessage, userInput.trim()), pe.getMessage());
        }
    }
}
