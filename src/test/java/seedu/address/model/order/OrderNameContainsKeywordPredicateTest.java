package seedu.address.model.order;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderNameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderNameContainsKeywordPredicate firstPredicate =
                new OrderNameContainsKeywordPredicate(firstPredicateKeywordList);
        OrderNameContainsKeywordPredicate secondPredicate =
                new OrderNameContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderNameContainsKeywordPredicate firstPredicateCopy =
                new OrderNameContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        OrderNameContainsKeywordPredicate predicate =
                new OrderNameContainsKeywordPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        OrderNameContainsKeywordPredicate predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new OrderBuilder().withName("Alice Bob").build()));
    }
}
