package seedu.address.testutil;

import seedu.address.model.route.Route;

/**
 * A utility class containing a list of {@code Route} objects to be used in tests.
 */
public class TypicalRoutes {
    public static final Route ALICE = new RouteBuilder()
            .withOrder("Alice Ang", "82930465", "6th Avenue #12", "Garlic Naan", "Milo")
            .withDeliveryman("Jack").build();

    public static final Route BENNY = new RouteBuilder()
            .withOrder("Benny Ng", "81730485", "31 Jalan Besar", "Kampong Fried Rice")
            .withDeliveryman("John").build();
}
