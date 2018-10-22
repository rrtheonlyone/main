package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.OrderBook;
import seedu.address.model.deliveryman.DeliverymenList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

public class XmlFoodZoomStorage {
    private static final Logger logger = LogsCenter.getLogger(XmlFoodZoomStorage.class);

    private Path orderBookFilePath;
    private Path deliverymenListFilePath;
    private Optional<OrderBook> orderBook;
    private Optional<DeliverymenList> deliverymenList;

    public XmlFoodZoomStorage(Path orderBookFilePath) {
        this.orderBookFilePath = orderBookFilePath;
        this.deliverymenListFilePath = deliverymenListFilePath;
    }

    public Path getOrderBookFilePath() {
        return orderBookFilePath;
    }

    public Path getDeliverymenListFilePath() {
        return deliverymenListFilePath;
    }

    public void readFoodZoomStorage() throws DataConversionException, IOException {
        readFoodZoomStorage(orderBookFilePath, deliverymenListFilePath);
    }

    public void readFoodZoomStorage(Path orderBookFilePath, Path deliverymenListFilePath) throws DataConversionException {

    }
}
