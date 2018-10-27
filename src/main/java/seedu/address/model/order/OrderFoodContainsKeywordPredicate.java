package seedu.address.model.order;

import java.util.List;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Food} matches any of the keywords given.
 */
public class OrderFoodContainsKeywordPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderFoodContainsKeywordPredicate(List<String> food) {
        keywords = food;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(keyword -> order.getFood().stream()
                .anyMatch(food -> StringUtil.containsWordIgnoreCase(food.foodName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderFoodContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((OrderFoodContainsKeywordPredicate) other).keywords)); // state check
    }
}
