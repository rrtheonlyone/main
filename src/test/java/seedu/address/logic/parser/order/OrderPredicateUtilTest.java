package seedu.address.logic.parser.order;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderNameContainsKeywordPredicate;

public class OrderPredicateUtilTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_emptyNameTag_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);

        ArgumentMultimap emptyName = tokenizeInput(" n/");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyName);
    }

    @Test
    public void test_emptyPhoneTag_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);

        ArgumentMultimap emptyPhone = tokenizeInput(" p/");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyPhone);
    }

    @Test
    public void test_emptyAddressTag_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);

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
    public void test_singlePredicate() throws ParseException {
        Predicate<Order> expectedPredicate;

        ArgumentMultimap emptyName = tokenizeInput(" n/alex");
        Predicate<Order> predicate = new OrderPredicateUtil().parsePredicate(emptyName);

        List<String> names = Collections.singletonList("alex");
        expectedPredicate = new OrderNameContainsKeywordPredicate(names);

        assertEquals(predicate, expectedPredicate);
    }

    private ArgumentMultimap tokenizeInput(String input) {
        return ArgumentTokenizer.tokenize(input, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_DATE, PREFIX_FOOD);
    }
}
