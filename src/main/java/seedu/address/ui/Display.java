package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.OrderPanelSelectionChangedEvent;
import seedu.address.model.order.Order;

/**
 * Panel containing the list of orders.
 */
public class Display extends UiPart<Region> {

    private static final String FXML = "Display.fxml";
    private final Logger logger = LogsCenter.getLogger(Display.class);

    public Display() {
        super(FXML);
    }
}
