package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyOrderBook;

/** Indicates the AddressBook in the model has changed*/
public class OrderBookChangedEvent extends BaseEvent {

    public final ReadOnlyOrderBook data;

    public OrderBookChangedEvent(ReadOnlyOrderBook data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of orders " + data.getOrderList().size();
    }
}
