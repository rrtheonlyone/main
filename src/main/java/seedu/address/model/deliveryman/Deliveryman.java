package seedu.address.model.deliveryman;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.TaggedObject;
import seedu.address.model.common.Name;
import seedu.address.model.order.Order;

/**
 * Represents a Deliveryman in FoodZoom.
 * Guarantees: has a name that is unique.
 */
public class Deliveryman extends TaggedObject {

    private final Name name;
    private final Set<Order> orders = new HashSet<>();

    public Deliveryman(Name name) {
        this(null, name, null);
    }

    public Deliveryman(Name name, Set<Order> orders) {
        this(null, name, orders);
    }

    /** This constructor is used when the {@code id} is specified. */
    public Deliveryman(UUID id, Name name, Set<Order> orders) {
        super(id);
        requireAllNonNull(name);
        this.name = name;
        this.orders.addAll(orders);
    }

    /**
     * This constructor is used to create a new copy of {@code deliveryman}.
     */
    public Deliveryman(Deliveryman deliveryman) {
        this(null, deliveryman.name, deliveryman.orders);
    }

    public Name getName() {
        return name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order o) {
        orders.add(o);
    }

    /**
     * Updates the order in the set with the edited order.
     */
    public void updateOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);
        assert(orders.contains(target));
        assert(!orders.contains(editedOrder));
        assert(target.isSameOrder(editedOrder));
        orders.remove(target);
        addOrder(editedOrder);
    }

    /**
     * Returns if this is the same common as {@code other}
     */
    public boolean isSameDeliveryman(Deliveryman other) {
        if (other == this) {
            return true;
        }

        return other != null && other.getName().equals(getName());
    }

    /**
     * Returns true if both deliverymen have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deliveryman)) {
            return false;
        }

        Deliveryman otherPerson = (Deliveryman) other;
        return otherPerson.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }

}
