package seedu.address.testutil;

import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.route.Route;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Route objects.
 */
public class RouteBuilder {

    public static final String DEFAULT_SOURCE = "12 Clementi Rd";
    public static final String DEFAULT_ORDER_NAME = "Alice Ng";
    public static final String DEFAULT_ORDER_PHONE = "84382150";
    public static final String DEFAULT_ORDER_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ORDER_FOOD = "Kampung Fried Rice";
    public static final String DEFAULT_ORDER_DELIVERYMAN = "Daniel Lee";

    private Address source;
    private Order order;
    private Deliveryman deliveryman;

    public RouteBuilder() {
        source = new Address(DEFAULT_SOURCE);
        order = new Order(new Name(DEFAULT_ORDER_NAME), new Phone(DEFAULT_ORDER_PHONE),
                new Address(DEFAULT_ORDER_ADDRESS), SampleDataUtil.getFoodSet(DEFAULT_ORDER_FOOD));
        deliveryman = new Deliveryman(new Name(DEFAULT_ORDER_DELIVERYMAN));
    }

    /**
     * Initializes the RouteBuilder with the data of {@code routeToCopy}.
     */
    public RouteBuilder(Route routeToCopy) {
        source = routeToCopy.getSource();
        order = routeToCopy.getOrder();
        deliveryman = routeToCopy.getDeliveryman();
    }

    /**
     * Sets the {@code source} of the {@code Route} that we are building.
     */
    public RouteBuilder withSource(String source) {
        this.source = new Address(source);
        return this;
    }

    /**
     * Sets the {@code order} of the {@code Route} that we are building.
     */
    public RouteBuilder withOrder(String name, String phone, String address, String... food) {
        this.order = new Order(new Name(name), new Phone(phone), new Address(address), SampleDataUtil.getFoodSet(food));
        return this;
    }

    /**
     * Sets the {@code source} of the {@code Route} that we are building.
     */
    public RouteBuilder withDeliveryman(String deliveryman) {
        this.deliveryman = new Deliveryman(new Name(deliveryman));
        return this;
    }

    public Route build() {
        return new Route(source, deliveryman, order);
    }

}
