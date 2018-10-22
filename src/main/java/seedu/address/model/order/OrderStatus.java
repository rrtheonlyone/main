package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Order's Status in the order book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)} (String)}
 */
public class OrderStatus {

    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Status should be either: PENDING, ONGOING or COMPLETED";

    private enum Status {
        PENDING,
        ONGOING,
        COMPLETED
    }

    private Status orderState = null;

    /**
     * Constructs a {@code OrderStatus}.
     */
    public OrderStatus() {
        this(Status.PENDING.name());
    }

    /**
     * Constructs a {@code OrderStatus}.
     */
    public OrderStatus(String status) {
        orderState = Status.valueOf(status);
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String orderStatus) {
        for (Status s : Status.values()) {
            if (orderStatus.equals(s.name())) {
                return true;
            }
        }

        return false;
    }


    @Override
    public String toString() {
        return orderState.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderStatus // instanceof handles nulls
                && orderState.equals(((OrderStatus) other).orderState)); // state check
    }

    @Override
    public int hashCode() {
        return orderState.hashCode();
    }

}
