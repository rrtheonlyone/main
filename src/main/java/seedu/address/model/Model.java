package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.route.ReadOnlyRouteList;
import seedu.address.model.route.Route;
import seedu.address.model.user.User;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Route> PREDICATE_SHOW_ALL_ROUTES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<User> PREDICATE_SHOW_ALL_USERS = unused -> true;

    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyOrderBook newData);

    /**
     * Returns the OrderBook
     */
    ReadOnlyOrderBook getOrderBook();

    /**
     * Returns true if a person with the same identity as {@code order} exists in the order book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     */
    void deleteOrder(Order target);

    /**
     * Adds the given order.
     * {@code order} must not already exist in the address book.
     */
    void addOrder(Order order);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order book.
     */
    void updateOrder(Order target, Order editedOrder);

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Returns true if the model has previous order book states to restore.
     */
    boolean canUndoOrderBook();

    /**
     * Returns true if the model has undone order book states to restore.
     */
    boolean canRedoOrderBook();

    /**
     * Restores the model's order book to its previous state.
     */
    void undoOrderBook();

    /**
     * Restores the model's order book to its previously undone state.
     */
    void redoOrderBook();

    /**
     * Saves the current order book state for undo/redo.
     */
    void commitOrderBook();

    boolean hasUser(User user);

    void addUser(User user);

    void commitUsersList();

    /**
     * Updates the filter of the filtered user list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredUsersList(Predicate<User> predicate);


    /**
     * Returns an unmodifiable view of the filtered users list
     */
    ObservableList<User> getFilteredUsersList();

    boolean loginUser(User user);

    /**
     * Returns the UsersList
     */
    ReadOnlyUsersList getUsersList();

    // ======================== Route related methods =========================

    /** Clears existing backing model and replaces with the provided new data. */
    void resetRouteData(ReadOnlyRouteList newData);

    /** Returns the RouteList */
    ReadOnlyRouteList getRouteList();

    /**
     * Returns true if a route with the same identity as {@code route} exists in the route list.
     */
    boolean hasRoute(Route route);

    /**
     * Deletes the given route.
     * The route must exist in the route list.
     */
    void deleteRoute(Route target);

    /**
     * Adds the given route.
     * {@code route} must not already exist in the route list.
     */
    void addRoute(Route route);

    /**
     * Replaces the given route {@code target} with {@code editedRoute}.
     * {@code target} must exist in the route list.
     * The route identity of {@code editedRoute} must not be the same as another existing route in the route list.
     */
    void updateRoute(Route target, Route editedRoute);

    /** Returns an unmodifiable view of the filtered route list */
    ObservableList<Route> getFilteredRouteList();

    /**
     * Updates the filter of the filtered route list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRouteList(Predicate<Route> predicate);

    /**
     * Returns true if the model has previous route list states to restore.
     */
    boolean canUndoRouteList();

    /**
     * Returns true if the model has undone route list states to restore.
     */
    boolean canRedoRouteList();

    /**
     * Restores the model's route list to its previous state.
     */
    void undoRouteList();

    /**
     * Restores the model's route list to its previously undone state.
     */
    void redoRouteList();

    /**
     * Saves the current route list state for undo/redo.
     */
    void commitRouteList();

}
