package seedu.address.logic.commands.route;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.ReadOnlyUsersList;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.deliveryman.DeliverymenList;
import seedu.address.model.order.Order;
import seedu.address.model.route.ReadOnlyRouteList;
import seedu.address.model.route.Route;
import seedu.address.model.route.RouteList;
import seedu.address.model.user.User;
import seedu.address.testutil.route.RouteBuilder;

public class CreateRouteCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullRoute_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new CreateRouteCommand(null);
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRouteAdded modelStub = new ModelStubAcceptingRouteAdded();
        Route validRoute = new RouteBuilder().withDestination("123 AMK Street").build();
        CommandResult commandResult = new CreateRouteCommand(validRoute).execute(modelStub, commandHistory);

        assertEquals(String.format(CreateRouteCommand.MESSAGE_SUCCESS, validRoute), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validRoute), modelStub.routesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateRoute_throwsCommandException() throws Exception {
        Route validRoute = new RouteBuilder().build();
        CreateRouteCommand addCommand = new CreateRouteCommand(validRoute);
        CreateRouteCommandTest.ModelStub modelStub = new CreateRouteCommandTest.ModelStubWithRoute(validRoute);

        thrown.expect(CommandException.class);
        thrown.expectMessage(CreateRouteCommand.MESSAGE_DUPLICATE_ROUTE);
        addCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Route angmokio = new RouteBuilder().withDestination("Ang Mo Kio").build();
        Route bedok = new RouteBuilder().withDestination("Bedok").build();
        CreateRouteCommand addAngmokioCommand = new CreateRouteCommand(angmokio);
        CreateRouteCommand addBedokCommand = new CreateRouteCommand(bedok);

        // same object -> returns true
        assertTrue(addAngmokioCommand.equals(addAngmokioCommand));

        // same values -> returns true
        CreateRouteCommand addAngmokioCommandCopy = new CreateRouteCommand(angmokio);
        assertTrue(addAngmokioCommand.equals(addAngmokioCommandCopy));

        // different types -> returns false
        assertFalse(addAngmokioCommand.equals(1));

        // null -> returns false
        assertFalse(addAngmokioCommand.equals(null));

        // different destination -> returns false
        assertFalse(addAngmokioCommand.equals(addBedokCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeliveryman(Deliveryman deliveryman) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyOrderBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetDeliverymenData(DeliverymenList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyOrderBook getOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DeliverymenList getDeliverymenList() {
            throw new AssertionError(" This message should not be called");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitOrderBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitUsersList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredUsersList(Predicate<User> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<User> getFilteredUsersList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isRegisteredUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUsersList getUsersList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isUserLoggedIn() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void storeUserInSession(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public User getLoggedInUserDetails() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetRouteData(ReadOnlyRouteList newData) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRouteList getRouteList() {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeliveryman(Deliveryman person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeliveryman(Deliveryman target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoute(Route route) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDeliveryman(Deliveryman target, Deliveryman editedDeliveryman) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRoute(Route target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deliveryman> getFilteredDeliverymenList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoute(Route route) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDeliverymenList(Predicate<Deliveryman> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoDeliverymenList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoDeliverymenList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoDeliverymenList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoDeliverymenList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitDeliverymenList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateRoute(Route target, Route editedRoute) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Route> getFilteredRouteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRouteList(Predicate<Route> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public boolean canUndoRouteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoRouteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoRouteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoRouteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitRouteList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single route.
     */
    private class ModelStubWithRoute extends CreateRouteCommandTest.ModelStub {
        private final Route route;

        ModelStubWithRoute(Route route) {
            requireNonNull(route);
            this.route = route;
        }

        @Override
        public boolean hasRoute(Route route) {
            requireNonNull(route);
            return this.route.isSameRoute(route);
        }
    }

    /**
     * A Model stub that always accept the route being added.
     */
    private class ModelStubAcceptingRouteAdded extends CreateRouteCommandTest.ModelStub {
        final ArrayList<Route> routesAdded = new ArrayList<>();

        @Override
        public boolean hasRoute(Route route) {
            requireNonNull(route);
            return routesAdded.stream().anyMatch(route::isSameRoute);
        }

        @Override
        public void addRoute(Route route) {
            requireNonNull(route);
            routesAdded.add(route);
        }

        @Override
        public void commitRouteList() {
            // called by {@code CreateRouteCommand#execute()}
        }

        @Override
        public ReadOnlyRouteList getRouteList() {
            return new RouteList();
        }
    }

}
