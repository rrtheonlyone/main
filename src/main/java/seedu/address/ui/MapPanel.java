package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;

/**
 * An UI component that displays a map along with placeholders for pending {@code Order}.
 */
public class MapPanel extends UiPart<Region> {

    private static final String FXML = "MapPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MapPanel.class);

    @FXML
    private GridPane mapPane;

    public MapPanel(ObservableList<Order> orderList) {
        super(FXML);
    }

}
