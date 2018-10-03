package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.user.User;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

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
}
