package seedu.address.ui.displayCards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

public class OrderDisplayCard extends UiPart<Region> {
    private static final String FXML = "displayCards/OrderDisplayCard.fxml";

    public final Order order;

    @FXML
    private Label name;
    @FXML
    private Label deliverymanName;

    public OrderDisplayCard(Order order) {
        super(FXML);
        this.order = order;
        name.setText(order.getName().fullName);
        deliverymanName.setText(getFullNameOrNull(order.getDeliveryman()));
    }

    private String getFullNameOrNull(Deliveryman deliveryman) {
        if (deliveryman == null) {
            return "Not assigned to deliveryman.";
        }
        return deliveryman.getName().fullName;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderDisplayCard)) {
            return false;
        }

        // state check
        OrderDisplayCard card = (OrderDisplayCard) other;
        return order.equals(card.order);
    }
}
