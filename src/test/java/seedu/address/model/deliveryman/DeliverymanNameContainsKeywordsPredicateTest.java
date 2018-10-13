package seedu.address.model.deliveryman;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.DeliverymanBuilder;

public class DeliverymanNameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DeliverymanNameContainsKeywordsPredicate firstPredicate =
                new DeliverymanNameContainsKeywordsPredicate(firstPredicateKeywordList);
        DeliverymanNameContainsKeywordsPredicate secondPredicate =
                new DeliverymanNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DeliverymanNameContainsKeywordsPredicate firstPredicateCopy =
                new DeliverymanNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different deliveryman -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        DeliverymanNameContainsKeywordsPredicate predicate =
                new DeliverymanNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new DeliverymanBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DeliverymanNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new DeliverymanBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DeliverymanNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new DeliverymanBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DeliverymanNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new DeliverymanBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeliverymanNameContainsKeywordsPredicate predicate =
                new DeliverymanNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DeliverymanBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new DeliverymanNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new DeliverymanBuilder().withName("Alice Bob").build()));
    }
}
