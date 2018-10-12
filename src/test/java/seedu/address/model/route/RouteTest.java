package seedu.address.model.route;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.TypicalRoutes.ROUTE_ALICE_BENSON;
import static seedu.address.testutil.TypicalRoutes.ROUTE_CARL;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.route.RouteBuilder;

public class RouteTest {

    @Test
    public void isSameRoute() {
        // same object -> returns true
        assertTrue(ROUTE_ALICE_BENSON.isSameRoute(ROUTE_ALICE_BENSON));

        // null -> returns false
        assertFalse(ROUTE_ALICE_BENSON.isSameRoute(null));

        // different orders -> returns false
        Set<Order> elleOrders = new HashSet<>();
        elleOrders.add(TypicalOrders.ELLE);
        Route editedRouteAliceBenson = new RouteBuilder(ROUTE_ALICE_BENSON).withOrders(elleOrders).build();
        assertFalse(ROUTE_ALICE_BENSON.isSameRoute(editedRouteAliceBenson));

        // different source -> returns false
        editedRouteAliceBenson = new RouteBuilder(ROUTE_ALICE_BENSON).withSource(VALID_ADDRESS_BOB).build();
        assertFalse(ROUTE_ALICE_BENSON.isSameRoute(editedRouteAliceBenson));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Route aliceBensonCopy = new RouteBuilder(ROUTE_ALICE_BENSON).build();
        assertTrue(ROUTE_ALICE_BENSON.equals(aliceBensonCopy));

        // same object -> returns true
        assertTrue(ROUTE_ALICE_BENSON.equals(ROUTE_ALICE_BENSON));

        // null -> returns false
        assertFalse(ROUTE_ALICE_BENSON.equals(null));

        // different type -> returns false
        assertFalse(ROUTE_ALICE_BENSON.equals(5));

        // different route -> returns false
        assertFalse(ROUTE_ALICE_BENSON.equals(ROUTE_CARL));

        // different source -> returns false
        Route editedRouteAliceBenson = new RouteBuilder(ROUTE_ALICE_BENSON).withSource(VALID_ADDRESS_BOB).build();
        assertFalse(ROUTE_ALICE_BENSON.equals(editedRouteAliceBenson));

        // different orders -> returns false
        Set<Order> elleOrders = new HashSet<>();
        elleOrders.add(TypicalOrders.ELLE);
        editedRouteAliceBenson = new RouteBuilder(ROUTE_ALICE_BENSON).withOrders(elleOrders).build();
        assertFalse(ROUTE_ALICE_BENSON.equals(editedRouteAliceBenson));

    }
}
