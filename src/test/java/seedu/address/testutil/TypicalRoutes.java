package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.route.Route;
import seedu.address.model.route.RouteList;

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

    public static final Route CHARLIE = new RouteBuilder()
            .withOrder("Charlie Ha", "91385736", "204 Syed Alwi Road", "Plain Naan")
            .withDeliveryman("James").build();

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static RouteList getTypicalRouteList() {
        RouteList rl = new RouteList();
        for (Route route : getTypicalRoutes()) {
            rl.addRoute(route);
        }
        return rl;
    }

    public static List<Route> getTypicalRoutes() {
        return new ArrayList<>(Arrays.asList(ALICE, BENNY, CHARLIE));
    }
}
