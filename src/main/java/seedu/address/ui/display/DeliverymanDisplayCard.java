package seedu.address.ui.display;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

/**
 * UI Component that displays relevant information of a deliveryman.
 */
public class DeliverymanDisplayCard extends UiPart<Region> {
    private static final String FXML = "display/DeliverymanDisplayCard.fxml";
    private static final String BUSY_LABEL_CLASS = "busy";
    private static final String AVAILABLE_LABEL_CLASS = "available";

    public final Deliveryman deliveryman;

    @FXML
    private Label name;
    @FXML
    private Label deliverymanStatus;
    @FXML
    private ListView<Order> orderListViewForCard;

    public DeliverymanDisplayCard(Deliveryman deliveryman) {
        super(FXML);
        this.deliveryman = deliveryman;
        name.setText(deliveryman.getName().fullName);
        setDeliverymanStatus();
        orderListViewForCard.setItems(FXCollections.observableArrayList(deliveryman.getOrders()));
        orderListViewForCard.setCellFactory(listView -> new OrderListViewCell());
    }

    private void setDeliverymanStatus() {
        if (deliveryman.getOrders().size() > 0) {
            deliverymanStatus.setText("Delivering");
            deliverymanStatus.getStyleClass().clear();
            deliverymanStatus.getStyleClass().add(BUSY_LABEL_CLASS);
        } else {
            deliverymanStatus.setText("Available");
            deliverymanStatus.getStyleClass().clear();
            deliverymanStatus.getStyleClass().add(AVAILABLE_LABEL_CLASS);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliverymanDisplayCard)) {
            return false;
        }

        // state check
        DeliverymanDisplayCard card = (DeliverymanDisplayCard) other;
        return deliveryman.equals(card.deliveryman);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code OrderCard}.
     */
    class OrderListViewCell extends ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderDisplayListCard(order).getRoot());
            }
        }
    }
}
