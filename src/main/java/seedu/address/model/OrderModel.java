package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;

/**
 * The API of the Model component.
 */
public interface OrderModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyOrderBook newData);

    /** Returns the AddressBook */
    ReadOnlyOrderBook getOrderBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteOrder(Order target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addOrder(Order order);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void updateOrder(Order target, Order editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoOrderBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoOrderBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoOrderBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoOrderBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitOrderBook();
}
