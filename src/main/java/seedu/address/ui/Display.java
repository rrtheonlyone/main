package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.DeliveryManPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.OrderPanelSelectionChangedEvent;
import seedu.address.ui.displayCards.DeliverymanDisplayCard;
import seedu.address.ui.displayCards.OrderDisplayCard;

/**
 * Panel containing the list of orders.
 */
public class Display extends UiPart<Region> {

    private static final String FXML = "Display.fxml";
    private final Logger logger = LogsCenter.getLogger(Display.class);

    @FXML
    private StackPane displayPanelPlaceholder;

    public Display() {
        super(FXML);
        registerAsAnEventHandler(this);
    }

    @Subscribe
    public void handleOrderPanelSelectionChangedEvent(OrderPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        displayPanelPlaceholder.getChildren().setAll(new OrderDisplayCard(event.getNewSelection()).getRoot());
    }

    @Subscribe
    public void handleDeliveryPanelSelectionChangedEvent(DeliveryManPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        displayPanelPlaceholder.getChildren().setAll(new DeliverymanDisplayCard(event.getNewSelection()).getRoot());
    }
}
