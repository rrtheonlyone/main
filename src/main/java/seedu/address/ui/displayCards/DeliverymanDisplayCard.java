package seedu.address.ui.displayCards;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.deliveryman.Deliveryman;
import seedu.address.model.order.Order;
import seedu.address.ui.OrderCard;
import seedu.address.ui.UiPart;

public class DeliverymanDisplayCard extends UiPart<Region> {
    private static final String FXML = "displayCards/DeliverymanDisplayCard.fxml";

    public final Deliveryman deliveryman;

    @FXML
    private Label name;
    @FXML
    private ListView<Order> orderListView;

    public DeliverymanDisplayCard(Deliveryman deliveryman) {
        super(FXML);
        this.deliveryman = deliveryman;
        name.setText(deliveryman.getName().fullName);
        orderListView.setItems(FXCollections.observableArrayList(deliveryman.getOrders()));
        orderListView.setCellFactory(listView -> new OrderListViewCell());
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
                setGraphic(new OrderCard(order, getIndex() + 1).getRoot());
            }
        }
    }
}
