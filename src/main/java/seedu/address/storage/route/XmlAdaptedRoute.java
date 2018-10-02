package seedu.address.storage.route;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.route.Route;
import seedu.address.storage.XmlAdaptedOrder;
import seedu.address.storage.deliveryman.XmlAdaptedDeliveryman;

/**
 * JAXB-friendly version of the Route.
 */
public class XmlAdaptedRoute {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Route's %s field is missing!";

    @XmlElement(required = true)
    private String source;
    @XmlElement(required = true)
    private String destination;
    @XmlElement
    private XmlAdaptedOrder order;
    @XmlElement(required = true)
    private XmlAdaptedDeliveryman deliveryman;

    /**
     * Constructs an XmlAdaptedRoute.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedRoute() {}

    /**
     * Constructs an {@code XmlAdaptedRoute} with the given route details.
     */
    public XmlAdaptedRoute(String source, String destination, XmlAdaptedOrder order, XmlAdaptedDeliveryman deliveryman) {
        this.source = source;
        this.destination = destination;
        this.order = order;
        this.deliveryman = deliveryman;
    }

    /**
     * Converts a given Route into this class for JAXB use.
     *
     * @param route future changes to this will not affect the created XmlAdaptedRoute
     */
    public XmlAdaptedRoute(Route route) {
        source = route.getSource().value;
        destination = route.getDestination().value;
        order = new XmlAdaptedOrder(route.getOrder());
        deliveryman = new XmlAdaptedDeliveryman(route.getDeliveryman());
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Route object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted route
     */
    public Route toModelType() throws IllegalValueException {
        if (source == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(source)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        final Address modelSource = new Address(source);

        if (destination == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(destination)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        final Address modelDestination = new Address(destination);

        final Order modelOrder = order.toModelType();

        final Deliveryman modelDeliveryman = deliveryman.toModelType();

        return new Route(modelSource, modelDeliveryman, modelOrder);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedRoute)) {
            return false;
        }

        XmlAdaptedRoute otherRoute = (XmlAdaptedRoute) other;
        return Objects.equals(source, otherRoute.source)
                && Objects.equals(destination, otherRoute.destination)
                && deliveryman.equals(otherRoute.deliveryman)
                && order.equals(otherRoute.order);
    }
}
