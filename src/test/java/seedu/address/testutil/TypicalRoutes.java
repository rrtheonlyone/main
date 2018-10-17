package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.order.Order;
import seedu.address.model.route.Route;
import seedu.address.model.route.RouteList;
import seedu.address.testutil.route.RouteBuilder;

/**
 * A utility class containing a list of {@code Route} objects to be used in tests.
 */
public class TypicalRoutes {
    private static final Order ALICE = TypicalOrders.ALICE;
    private static final Order BENSON = TypicalOrders.BENSON;
    private static final Order CARL = TypicalOrders.CARL;
    private static final Order DANIEL = TypicalOrders.DANIEL;

    public static final Route ROUTE_ALICE_BENSON = new RouteBuilder()
            .withOrder(ALICE).withOrder(BENSON).build();

    public static final Route ROUTE_CARL = new RouteBuilder()
            .withOrder(CARL).build();

    public static final Route ROUTE_DANIEL = new RouteBuilder()
            .withOrder(DANIEL).build();

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
        return new ArrayList<>(Arrays.asList(ROUTE_ALICE_BENSON, ROUTE_CARL, ROUTE_DANIEL));
    }
}
