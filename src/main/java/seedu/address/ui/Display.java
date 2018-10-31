package seedu.address.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

import seedu.address.commons.events.ui.OrderPanelSelectionChangedEvent;
import seedu.address.model.order.Order;

import seedu.address.commons.events.ui.DeliveryManPanelSelectionChangedEvent;
import seedu.address.ui.display.DeliverymanDisplayCard;
import seedu.address.ui.display.OrderDisplayCard;

/**
 * Panel containing the main display - map and side content.
 */
public class Display extends UiPart<Region> {

    private static final String FXML = "Display.fxml";
    private final Logger logger = LogsCenter.getLogger(Display.class);

    @FXML
    private StackPane mapWrapper;

    @FXML
    private StackPane displayPanelPlaceholder;

    private MapPanel mapPanel;
    private ObservableList<Order> orderList;
    private HashMap<String, Integer> directory;

    public Display(ObservableList<Order> orderList) {
        super(FXML);
        this.orderList = orderList;

        fillInnerParts();
        setupMap();

        this.orderList.addListener((ListChangeListener.Change<? extends Order> change) -> {
            while (change.next()) {
                if (change.wasUpdated()) {

                    directory = new HashMap<>();
                    for (Order o : change.getList()) {
                        logger.info("UPDATE " + o.toString());
                        if (o.getOrderStatus().toString().equals("PENDING")) {
                            String postalCode = o.getAddress().getPostalCode();
                            String postalCodeKey = postalCode.substring(0, 2);
                            if (directory.containsKey(postalCodeKey)) {
                                directory.put(postalCodeKey, directory.get(postalCodeKey) + 1);
                            } else {
                                directory.put(postalCodeKey, 1);
                            }
                        }
                    }

                    mapPanel.clear();
                    mapPanel.initialise(directory);

                } else {
                    for (Order o : change.getRemoved()) {
                        logger.info("REMOVED " + o.toString());
                        if (o.getOrderStatus().toString().equals("PENDING")) {
                            String postalCode = o.getAddress().getPostalCode();
                            String postalCodeKey = postalCode.substring(0, 2);
                            if (directory.containsKey(postalCodeKey)) {
                                if (directory.get(postalCodeKey) <= 1) {
                                    directory.remove(postalCodeKey);
                                } else {
                                    directory.put(postalCodeKey, directory.get(postalCodeKey) - 1);
                                }
                            }
                        }
                    }

                    for (Order o : change.getAddedSubList()) {
                        logger.info("ADDED " + o.toString());
                        if (o.getOrderStatus().toString().equals("PENDING")) {
                            String postalCode = o.getAddress().getPostalCode();
                            String postalCodeKey = postalCode.substring(0, 2);
                            if (directory.containsKey(postalCodeKey)) {
                                directory.put(postalCodeKey, directory.get(postalCodeKey) + 1);
                            } else {
                                directory.put(postalCodeKey, 1);
                            }
                        }
                    }

                    mapPanel.clear();
                    mapPanel.initialise(directory);
                }
            }
        });

        registerAsAnEventHandler(this);
    }

    private void fillInnerParts() {
        mapPanel = new MapPanel();
        mapWrapper.getChildren().add(mapPanel.getRoot());
    }

    private void setupMap() {
        directory = new HashMap<>();
        for (Order o : orderList) {
            // map all pending orders
            if (o.getOrderStatus().toString().equals("PENDING")) {
                String postalCode = o.getAddress().getPostalCode();
                String postalCodeKey = postalCode.substring(0, 2);

                if (directory.containsKey(postalCodeKey)) {
                    directory.put(postalCodeKey, directory.get(postalCodeKey) + 1);
                } else {
                    directory.put(postalCodeKey, 1);
                }
            }
        }

        mapPanel.initialise(directory);
    }




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
