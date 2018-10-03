package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.OrderBookChangedEvent;
import seedu.address.model.order.Order;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedOrderBook versionedOrderBook;
    private final FilteredList<Order> filteredOrders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyOrderBook orderBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(orderBook, userPrefs);

        logger.fine("Initializing with order book: " + orderBook + " and user prefs " + userPrefs);

        versionedOrderBook = new VersionedOrderBook(orderBook);
        filteredOrders = new FilteredList<>(versionedOrderBook.getOrderList());
    }

    public ModelManager() {
        this(new OrderBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyOrderBook newData) {
        versionedOrderBook.resetData(newData);
        indicateOrderBookChanged();
    }

    @Override
    public ReadOnlyOrderBook getOrderBook() {
        return versionedOrderBook;
    }

    /**
     * Raises an event to indicate the model has changed
     */
    private void indicateOrderBookChanged() {
        raise(new OrderBookChangedEvent(versionedOrderBook));
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return versionedOrderBook.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        versionedOrderBook.removeOrder(target);
        indicateOrderBookChanged();
    }

    @Override
    public void addOrder(Order order) {
        versionedOrderBook.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        indicateOrderBookChanged();
    }

    @Override
    public void updateOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        versionedOrderBook.updateOrder(target, editedOrder);
        indicateOrderBookChanged();
    }

    //=========== Filtered Orders List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return FXCollections.unmodifiableObservableList(filteredOrders);
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoOrderBook() {
        return versionedOrderBook.canUndo();
    }

    @Override
    public boolean canRedoOrderBook() {
        return versionedOrderBook.canRedo();
    }

    @Override
    public void undoOrderBook() {
        versionedOrderBook.undo();
        indicateOrderBookChanged();
    }

    @Override
    public void redoOrderBook() {
        versionedOrderBook.redo();
        indicateOrderBookChanged();
    }

    @Override
    public void commitOrderBook() {
        versionedOrderBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedOrderBook.equals(other.versionedOrderBook)
                && filteredOrders.equals(other.filteredOrders);
    }

}
