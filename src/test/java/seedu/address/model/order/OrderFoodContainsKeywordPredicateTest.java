package seedu.address.model.order;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderFoodContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderFoodContainsKeywordPredicate firstPredicate =
                new OrderFoodContainsKeywordPredicate(firstPredicateKeywordList);
        OrderFoodContainsKeywordPredicate secondPredicate =
                new OrderFoodContainsKeywordPredicate(secondPredicateKeywordList);

        // Same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // Same values -> returns true
        OrderFoodContainsKeywordPredicate firstPredicateCopy =
                new OrderFoodContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // Different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // Different food -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_foodContainsKeywords_returnsTrue() {
        // One keyword
        OrderFoodContainsKeywordPredicate predicate =
                new OrderFoodContainsKeywordPredicate(Collections.singletonList("rice"));
        assertTrue(predicate.test(new OrderBuilder().withFood("fried rice").build()));

        // Multiple keywords
        predicate = new OrderFoodContainsKeywordPredicate(Arrays.asList("fried", "rice"));
        assertTrue(predicate.test(new OrderBuilder().withFood("fried rice").build()));

        // Only one matching keyword
        predicate = new OrderFoodContainsKeywordPredicate(Arrays.asList("tea", "rice"));
        assertTrue(predicate.test(new OrderBuilder().withFood("fried rice").build()));

        // Keyword match only 2nd food
        predicate = new OrderFoodContainsKeywordPredicate(Arrays.asList("rice"));
        assertTrue(predicate.test(new OrderBuilder().withFood("milo", "fried rice").build()));

        // Mixed-case keywords
        predicate = new OrderFoodContainsKeywordPredicate(Arrays.asList("fRiEd", "RiCE"));
        assertTrue(predicate.test(new OrderBuilder().withFood("Fried Rice").build()));
    }

    @Test
    public void test_foodDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        OrderFoodContainsKeywordPredicate predicate = new OrderFoodContainsKeywordPredicate(Arrays.asList("tea"));
        assertFalse(predicate.test(new OrderBuilder().withFood("fried rice").build()));

        // Match no food
        predicate = new OrderFoodContainsKeywordPredicate(Arrays.asList("tea"));
        assertFalse(predicate.test(new OrderBuilder().withFood("fried rice", "ice milo", "roti canai").build()));
    }
}
