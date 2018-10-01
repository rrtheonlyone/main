package seedu.address.testutil;

import seedu.address.model.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ALICE = new OrderBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withPhone("94351253")
            .withFood("Roti Prata").build();

    public static final Order BENSON = new OrderBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPhone("98765432")
            .withFood("Egg-Fried Rice", "Milo").build();


    private TypicalOrders() {} // prevents instantiation
}
