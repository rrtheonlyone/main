package seedu.address.ui.display;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

public class OrderPrintOut extends UiPart<Region> {

    private static final String FXML = "display/OrderPrintOut.fxml";

    private final Order order;

    @FXML
    private Label namePO;

    @FXML
    private Label datePO;

    @FXML
    private Text addressPO;

    @FXML
    private Label phonePO;

    public OrderPrintOut(Order order) {
        super(FXML);
        this.order = order;

        namePO.setText("Order from " + order.getName().fullName);
        datePO.setText(order.getDate().toString());
        phonePO.setText(order.getPhone().toString());
        addressPO.setText(order.getAddress().toString());
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderPrintOut)) {
            return false;
        }

        // state check
        OrderPrintOut card = (OrderPrintOut) other;
        return order.equals(card.order);
    }

}



