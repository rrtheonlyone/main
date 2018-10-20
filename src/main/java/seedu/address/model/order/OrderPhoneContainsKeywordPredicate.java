package seedu.address.model.order;

import java.util.function.Predicate;

/**
 * Tests that a {@code Order}'s {@code Phone} matches any of the keywords given.
 */
public class OrderPhoneContainsKeywordPredicate implements Predicate<Order> {
    private final String keyword;

    public OrderPhoneContainsKeywordPredicate(String phone) {
        phone = removeAllWhiteSpaces(phone);
        keyword = phone;
    }

    @Override
    public boolean test(Order order) {
        return order.getPhone().value.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderPhoneContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((OrderPhoneContainsKeywordPredicate) other).keyword)); // state check
    }

    /**
     * Remove all trailing and leading whitespace and whitespaces in between {@code input}
     */
    private String removeAllWhiteSpaces(String input) {
        return input.trim().replaceAll("\\s+", "");
    }
}
