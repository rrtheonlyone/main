package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.deliveryman.DeliverymenList;

public class FoodZoomChangedEvent extends BaseEvent {

    public final ReadOnlyOrderBook orderBook;

    public final DeliverymenList deliverymenList;

    public FoodZoomChangedEvent(ReadOnlyOrderBook orderBook, DeliverymenList deliverymenList) {
        this.orderBook = orderBook;
        this.deliverymenList = deliverymenList;
    }

    @Override
    public String toString() {
        return "number of orders " + orderBook.getOrderList().size() + deliverymenList.getDeliverymenList().size();
    }
}
